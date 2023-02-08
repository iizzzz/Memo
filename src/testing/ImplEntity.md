    // ------------------ Test 를 위한 팩토리 메서드 ------------------
    private Post(Long postId, String title, String content, Kind kinds) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.kinds = kinds;
    }

    public static Post of(Long id, String title, String content, Kind kinds) {
        return new Post(id,title,content,kinds);
    }

    // ------------------ Test 를 위한 팩토리 메서드 ------------------