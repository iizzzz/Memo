# โญ Git ์ ๋ฆฌ

> ## ๐ Git ์ด๋?

* ํ์ผ๋ค์ ๋ณ๊ฒฝ์ฌํญ ์ถ์ , ๋ฐฑ์, ๋ฒ์ ๊ด๋ฆฌ
* ๊ณต๋์์๋ฌผ ์ทจํฉ

> ## ๐ Git SSH ๋ฑ๋ก

* cmd - ssh-keygen - public key ๋ด์ฉ ๋ณต์ฌ
* github์ SSH Key ๋ฑ๋ก

* in Linux
  * curl -fsSL https://cli.github.com/packages/githubcli-archive-keyring.gpg | sudo dd of=/usr/share/keyrings/githubcli-archive-keyring.gpg
  * echo "deb [arch=$(dpkg --print-architecture) signed-by=/usr/share/keyrings/githubcli-archive-keyring.gpg] https://cli.github.com/packages stable main" | sudo tee /etc/apt/sources.list.d/github-cli.list > /dev/null
  * apt update
  * apt install gh dirmngr
  * gh auth login
  ![sc](images/gh_auth_login.png)
  * CLI์์ ๋ฐ์ Code๋ฅผ https://github.com/login/device ๋ก ์ด๋ํด์ ์ธ์ฆ
  * gh auth status & gh auth logout