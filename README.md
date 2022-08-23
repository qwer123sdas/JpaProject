# 개인 프로젝트 : MyBatis에서 JPA로 마이그레이션 하기

</br>

## **기술 환경**
- JAVA 11
- Spring Boot 2.7
- H2
- JPA


</br>

## *목적*
기존에 Mabtis로 포인트 적립 프로세스를 만든 프로젝트를 JPA로 변경하고자 시작한 프로젝트입니다.</br>
공부를 통해 배웠던 엔티티와 도메인 중심의 설계를 적용하였습니다.


</br>

## ERD
![ERD](./image/JPA2.png)

</br>


## 포인트 적립 조건
해당 장소에 첫번 째 리뷰 :  1점, </br>
리뷰에 1자 이상의 텍스트 작성 :  1점, </br>
사진을 1장 이상 첨부 : 1점

</br>

## 중점을 둔 부분
- 도메인 중심 설계
보다 객체 지향적인 코드가 되어 유지 보수하기 쉽도록 하기 위해 </br>
도메인에 해당 필드에 맞는 Builder를 만들었습니다.

- 최적화
유저마다 적립한 포인트를 조회하기 위해 쿼리를 날리기 보다, 미리 유저 엔티티에 저장해 놓기 </br>
리뷰마다 해당되는 포인트를 표시해 놓아, 사용된 포인트와 적립된 포인트, 그리고 ??? </br>
생각해보니까 왜 저렇게 햇냐

