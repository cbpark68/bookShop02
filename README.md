데모용 도서쇼핑몰 개요
- 이병승님의 도서 '자바웹을 다루는 기술'에 포함된 도서쇼핑몰을 기반으로 합니다.
- 전자정부프레임워크 4.1.0 eGovWebProject로 프로로젝트 생성 후 java소스는 모두 직접 입력했으며 jsp와 설정파일은 임포트 후 필요시 수정했습니다.
- 사용한 DB는 오라클11xe 이며 운영체제는 우분투22 입니다.
- 화면구성에는 tiles를 이용했습니다.
- 파일업로드를 위해서 common-io,common-fileupload를 이용했습니다.
- 썸내일생성을 위해서 coobird를 이용했습니다.
- 메일발송을 위해 javax.mail을 이용했습니다.
- json처리를 위해 goodlecode.json-simple를 이용했습니다.

이 프로젝트의 용도
- 제가 스프링프레임워크 백앤드 java는 이정도 다둘수 있다는 것을 보여드리기 위해서 만든 소스입니다.
- 실무에서 작업한 소스는 외부유출이 불가능하므로 공개된 쇼핑몰소스를 이용했습니다.

아키텍처 개요
- 컨트롤러-서비스-DAO로 구성된 일반적인 스프링MVC 아키텍처로 구성되었습니다.
- 모든 클래스는 먼저 인터페이스를 만들고 이를 구현했습니다.

소개자료PPT
https://docs.google.com/presentation/d/1F313fTjPbJH69n2wtdiBOuZafo-dKIh5rXhvuCdvZ7M/edit?usp=sharing

끝.
