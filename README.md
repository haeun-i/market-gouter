# market-gouter  
## 식품 구매 및 레시피 공유 커뮤니티 웹 사이트 개발 프로젝트    

### 프로젝트 목적  
> '마켓 컬리' 사이트와 유사하게, 식품을 위주로 판매하는 웹 사이트를 개발한다. 이와 더불어 사이트 이용자 간의 레시피를 자유롭게 공유할 수 있는 커뮤니티 게시판 기능을 포함하여 상품 판매 및 레시피 교류의 장을 만든다. 

### 개발 언어  
> Spring Boot 2.4.9 version  
> Java 11  
> Gradle Project  
> Dependencies : MySQL, Lombok, Spring Web, Spring Data Jpa

### 담당 기능
* 김하은[backend]
:상품 주문, 장바구니 API 개발
* 정다빈[backend]
:로그인, 회원가입, 회원 수정, 회원탈퇴, 회원조회 API 개발
* 김유성[backend]
:레시피 관련 API 개발
* 김민겸[backend]
:상품 등록, 조회, 수정, 삭제 및 질문 게시판 CRUD API 개발

*** 

### Intellij 설치 방법

**1. 공식 사이트 접속 및 다운로드**

<https://www.jetbrains.com/idea/>

![인텔리제이 첫 화면](https://user-images.githubusercontent.com/71184046/147047652-926bddd8-811e-4c14-9f6c-7f8d1f67520b.png)

**2. 개발환경(Windows, macOS)에 맞춰 Community 버전 설치**

<img src="https://user-images.githubusercontent.com/71184046/147048371-987e59e7-622e-42d2-ac66-5e08ba48da56.png" width="450" height="350"/>

**3. .exe 파일을 실행 및 next 클릭**

<img src="https://user-images.githubusercontent.com/71184046/147049315-207507d8-a8a4-4ba5-8c42-98208394e36c.png" width="450" height="350"/>

**4. 경로 설정**

<img src="https://user-images.githubusercontent.com/71184046/147049478-cb77769d-5f76-484d-af8a-19808202f8f7.png" width="450" height="350"/>

**5. 필요한 옵션 체크**

<img src="https://user-images.githubusercontent.com/71184046/147049738-99cd8b62-614a-4eb1-aaf4-34732a973c4f.png" width="450" height="350"/>

**6. 설치 및 완료**

<img src="https://user-images.githubusercontent.com/71184046/147051122-56d39fa0-36dd-4c2b-9a3a-c0d3f6518a79.png" width="450" height="350"/>

<br/>

### Java 11 설치 방법

**1. OS에 맞는 msi 파일 다운로드**
https://aws.amazon.com/ko/corretto/

**2. 환경변수 설정**
> 제어판 - 시스템 및 보안 - 고급 시스템 설정 - 환경 변수 - 시스템 변수 - 새로 만들기
![image](https://user-images.githubusercontent.com/67851124/147070110-13673c56-686f-46a0-ad98-0f40cb3af9f4.png)
사진과 같이 시스템 변수 Path 값을 추가한다.

**3. 정상 설치 확인**
> java --version을 입력하여 Java가 정상적으로 설치 되었는지 확인한다. 
 ![image](https://user-images.githubusercontent.com/67851124/147070693-021e5026-1680-49d1-b5d7-e7f5b38db78f.png)

<br/>

### Intellij, jdk 연동
<img width="556" alt="image" src="https://user-images.githubusercontent.com/76279010/147068817-39ee9d4c-14f3-4d98-9b4d-ddb763a0b4f1.png">
File - Project Structure - 좌측 메뉴 중 project 접속 후 Project SDK에서 설치한 버전의 JDK를 선택하여 연동한다.<br/>
만일 해당 옵션이 자동으로 생기지 않았을 경우 Project SDK의 선택창 아래에서 Add JDK 클릭 후 JDK 경로를 직접 선택/입력한다.

