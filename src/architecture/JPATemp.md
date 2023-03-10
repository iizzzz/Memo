# Spring Data Jpa
> ## __Architecture Of JPA__

> ⭐ @Entity
JPA 엔티티 지정
***
> ⭐ @Id, @GeneratedValue
식별자, 전략 지정
***
> ⭐ tx.begin()
트랜잭션 시작
***
> ⭐ persist()
영속성 컨텍스트에 저장
***
> ⭐ tx.commit()
DB 테이블에 저장, 내부적으로 em.flush()가 호출됨
***
> ⭐ find(타입, 식별자값)
영속성 컨텍스트에서 조회
***
> ⭐ em.remove()
영속성 컨텍스트의 1차캐시 엔티티 제거
***
> ⭐ em.flush()
영속성 컨텍스트의 변경사항을 테이블에 반영
***
###### ⭐ Mapping
객체 - 테이블
필드 - 컬럼
엔티티 - 엔티티
기본키
***

Archetecture
===

⭐ Exception
* ErrorResponse
    * [멤버]
        * status
        * message
        * Inner Class FieldError
            * FieldError 클래스의.getFieldErrors를 호출해 stream으로 배치 -> 새로운 FieldError 객체 생성
            * getFieldErrors에서 얻은 Field, RejectedValue, DefaultMessage를 새 객체에 넣어서
            * List 변환후 리턴

    * 직접적인 에러 처리
    * ResponseBody의 필드 = ErrorResponse의 멤버
    * 특정 Exception 발생 시, 그 특정 Exception을 위한 of 메소드를 각각 작성
    *

* GlobalExceptionAdvice
    * 처리된 에러를 응답으로 전달

* BusinessLogicException
    * 적절한 Custom 상태코드 반환


⭐ Service
* Repository.findAll(PageRequest.of(page, size, Sort)


⭐ Auditable(Abstract class)
* @Getter, @MappedSuperclass, @EntityListeners(AuditingEntityListener.class)
* 엔티티마다 공통존재하면 엔티티 생성일, 수정일, 작성자 등의 필드 공통화하여
* 엔티티에 대한 이벤트 발생시 해당 필드의 값을 자동으로 채워줌


⭐ Pagenation
* [Controller] - Service로 page 생성 요청
* [Service] - page 생성 후 Controller로 반환
* [MultiResponseDto]
* page의 content 정보를 통해 CoffeeResponseDto 생성(List 타입으로)
* page로부터 pageInfo 생성
* 클라이언트의 쿼리파라미터 요청(page, size) -> 컨트롤러 (페이지 -1)
* 멀티ResponseDto는 응답으로 전송해야하기 떄문에 page.getNumber() +1 을 해줌


⭐ Coffee
[DTO]
* Patch(Get)
    * coffeeId(set), korName, engName, price, coffeeStatus
* Post(Get)
    * korName, engName, price, coffeeCode
* response(Get,Builder)
    * coffeeId, korName, engName, price, coffeeStatus

[Entity]
* coffeeId
* korName
* engName
* price
* coffeeCode
* createdAt
* modifiedAt
* coffeeStatus
* enum

<strong>OneToMany</strong>
* OrderCoffee

[Mapper] @Mapper(componentModel = "spring")
* post
    * coffee
* patch
    * coffee
* coffee
    * response
* coffees
    * List<response>

[Repository]
* extends
    * JpaRepository<Coffee, Long>
* Optional<Coffee>
    * findByCoffeeCode(String coffeeCode)

* @Query(JQPL) Optional<Coffee>
    * findByCoffee(long coffeeId)

[Service]
* DI
    * CoffeeRepository

public Coffee -> findVerifiedCoffee(long coffeeId) -> Optional  findById
private void -> verifyExistCoffee(String coffeeCode) -> Optional  findByCoffeeCode
private Coffee -> findVerifiedCoffeeByQuery(long coffeeId) -> Optional  findByCoffee

create(Coffee) ->  verifyExistCoffee(CoffeeCode 검색), setCoffeeCode
update(Coffee) ->  findVerifiedCoffee(id 검색), Optional + setProperty
find(coffeeId) ->  findVerifiedCoffee(id 검색)
finds(page,size) ->  findAll, PageRequest.of(page,size,sort)
delete(Long) ->  findVerifiedCoffee(id 검색), repo.delete

[Controller]
DI -> Service, Mapper

post(dto) -> mapped, singleResponse
patch(coffeeId, dto) -> dto.setCoffeeId, mapped, singleResponse
get(coffeeId) -> service.findCoffee, singleResponse
gets(Param:page,size) -> <Page>service.findCoffees(page-1, size),
<List> page.getcontent, Multi
delete(coffeeId) -> service.delete, responseEntity<>(HttpStatus)

⭐ Order

[DTO]
OrderCoffee(get) -> coffeeId, quantity
OrderCoffeeResponse(builder, get)-> coffeeId,quantity(Integer),korName,engName,price(Integer)
OrderPatch(get) -> orderId,orderStatus, setorderId()
OrderPost(get) -> memberId, @NotNull & @Valid List<OrderCoffeeDto>
OrderResponse(getset) -> orderId, memberId, orderStatus, List<OrderCoffeeResponseDto>, createdAt

[Entity]
orderId, orderStatus, createdAt, modifiedAt, enum

ManyToOne -> Member
OneToMany -> OrderCoffee

[Mapper] @Mapper(componentModel = "spring")

Patch -> Order

List<Order> -> List<OrderResponse>

postDto의 멤버 = memberId, List<OrderCoffeeDto>
OrderCoffee의 멤버 = coffeeId, quantity
Post -> Order {
new Order order
new Member member
member.setMemberId(Dto.getId); ** dto에서 가져온 memberId를 넣음 **

    DTO로 받은 요청 정보를 가공하여 OrderCoffee List에 담음
    List<OrderCoffee> = Dto.getOrderCoffees.stream()  <strong> DTO에서 받은 정보중 OrderCoffees를 가져와서 stream 배치 </strong>
        .map(A -> {
            new OrderCoffee orderCoffee
            new Coffee coffee
            coffee.setCoffeeId(A.getCoffeeId)  ** Dto에서 가져온 ID를 커피에 입력 **
            orderCoffee.setOrder(order) ** orderCoffee에 order 입력 **
            orderCoffee.setCoffee(coffee) ** orderCoffee에 coffeeId가 들어간 coffee 입력 **
            orderCoffee.setQuantity(A.getQuantity) ** orderCoffee에 Dto에서 가져온 quantity 입력 **
            return orderCoffee //i 리턴
        }
    //i order에 가공한 데이터와 memberId를 가지는 member를 넣는다
    order.setMember(member)
    order.setOrderCoffees(orderCoffees)
    return order
    }
    
    
    파라미터인 OrderCoffee의 price만 가공후 OrderCoffeeResponse에 OrderCoffee의 요소를 넣음
    List<OrderCoffees> -> List<OrderCoffeeResponseDto> (List<OrderCoffee>) {
        
        return orderCoffees.stream()
            .map(orderCoffee -> {
            //i 주문 등록시에는 price가 null이므로 null 여부 체크
            Money coffeePrice = orderCoffee.getCoffee.getPrice();
            Integer price = coffeePrice != null ? coffeePrice.getValue() : null;
            
            return OrderCoffeeResponseDto
                .builder()
                .coffeeId(orderCoffee.getCoffee.getCoffeeId)
                .quantity(orderCoffee.getQuantity())
                .price(price)
                .korName(orderCoffee.getCoffee.getKorName())
                .engName(orderCoffee.getCoffee.getEngName())
                .build();
                .collect(Collectors.toList());
            }
}

[Repository] extends JpaRepository<Order, Long>

[Service]
DI -> memberService, orderRepository

findVerifiedOrder -> ID 검색

create(Order) -> memservice.findVerifiedMem(order.getmem.getmemId), save
update(Order) -> findVerified(id검색), Optional(order.getorderstatus), setModifiedAt
find(id) -> findVerifiedOrder
finds(page,size) -> findAll(page, size, Sort)
cancel(id)
public void cancelOrder(long orderId) {
Order findOrder = findVerifiedOrder(orderId);
int step = findOrder.getOrderStatus().getStepNumber();

        if (step >= 2)
            throw new BusinessLogicException(ExceptionCode.CANNOT_CHANGE_ORDER);

        findOrder.setOrderStatus(Order.OrderStatus.ORDER_CANCEL);
        findOrder.setModifiedAt(LocalDateTime.now());
        orderRepository.save(findOrder);
    }


⭐ DTO
[SingleResponseDto<T>] @All @Getter
private T data;

[MultiResponseDto<T> (List<T> data, Page page)]  @Getter
private List<T> data;
private PageInfo pageInfo

Contructor(List<T> data, Page page) {
this.data=data;
this.PageInfo=new PageInfo(Page.getNumber+1, Page.getSize, Page.getTotalEle,Page.getTotalPages);
}

[PageInfo] @Getter @All
private int page
private int size
private long totalElements
private int totalPages

⭐ Stamp  @Get @Set @NoArg @Entity
private Long stampId;
private int stampCount;

//i Member와 매핑
@OneToOne, @JoinColume
private Member member
public void setMember(Member member) {
this.member = member;
if(member.getStamp() != this
member.setStamp(this)
}

<br>

---