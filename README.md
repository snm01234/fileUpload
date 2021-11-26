
## 스프링부트 파일업로드 / 다운로드 기능 연습

spring boot fileupload example

참고: https://goodteacher.tistory.com/351

dependencies는 spring web, thymeleaf, spring devtools, lombok 사용

application.properties에 파일 저장할 경로 설정

FileDto 만들고

HomeController에 upload/ download 기능 구현 

upload는 form 태그 이용해서 /upload호출

download는 thymleaf로 경로 지정
localhost:8080/downloads?uuid=파일이름; 식으로 파일 이름을 파라미터로 /download 호출 

### 수행결과
-------------

단일 파일 또는 다중파일 업로드가 가능 

![bandicam 2021-11-26 12-16-58-135](https://user-images.githubusercontent.com/55654216/143523191-09ca0515-85ee-449c-b2e9-81643acb74ea.jpg)
![bandicam 2021-11-26 12-13-13-709](https://user-images.githubusercontent.com/55654216/143523206-053fa177-a58e-4759-9831-0f26c72c4e0b.jpg)

파일 제출 시 upload 파일 목록으로 이동하고, 업로드파일 목록에서는 업로드한 파일을 다운로드 할 수 있음.

![bandicam 2021-11-26 12-13-16-820](https://user-images.githubusercontent.com/55654216/143523220-3c235279-9ac1-4ff8-aaaa-a8fe115f9d59.jpg)

파일 다운로드도 잘 동작하고

![bandicam 2021-11-26 12-13-34-354](https://user-images.githubusercontent.com/55654216/143523491-1cc01c77-8264-4507-912e-ff4681fc8689.jpg)

지정된 공간에 파일이 잘 업로드 되어있고 파일 이름 앞에 고유 uuid가 적용되어있는 것을  

![bandicam 2021-11-26 12-14-50-204](https://user-images.githubusercontent.com/55654216/143523237-3759ded3-a337-46a5-897d-3808b0be06b0.jpg)
