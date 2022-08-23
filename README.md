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

## 사용 방법

```
{
    "type": "REVIEW",
    "action": "ADD",
    "reviewId": "ff35e929-fcf6-11ec-b3c2-0242ac170002",
    "userId": "31313130-3031-3131-3130-000000000000",
    "placeId": "8040a09f-fcf6-11ec-b3c2-0242ac170002",
    "content": "좋아요!",
    "attachedPhotoIds": ["48925641-70f3-4674-86e6-420bbab59bf8", "cf00ec57-563b-4f0e-b5bf-78ce28738efb"]
}
```

PostMan을 통한, 리뷰 작성 이벤트 API인 POST http://localhost:8080/events

</br>

## 중점을 둔 부분
- 도메인 중심 설계</br>
보다 객체 지향적인 코드가 되어 유지 보수하기 쉽도록 하기 위해 </br>
도메인에 해당 필드에 맞는 Builder를 만들었습니다.

- 최적화</br>
유저마다 적립한 포인트를 조회하기 위해 쿼리를 날리기 보다, 미리 유저 엔티티에 저장해 놓았습니다. </br>
포인트는 세부 포인트 도메인(Mileage) & 종합 포인트 도메인(임시로 Users) 외에 레파지토리로 select하기 보다 간단하게 리뷰 도메인에서 바로 꺼내 쓸 수 있도록 하였습니다.</br>

