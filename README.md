# 2주차 
## 이츠 페이먼츠 ( EST's-Payments )
이스트소프트는 최근 금융사업과 관련하여 송금 서비스를 제공하기로 결정되었다. 각 서비스들은 MSA로 분리되어있다. 
<br> 

베타서비스를 위해, A2개발팀은 송금과 관련된 요구사항이 부여되었고 개발자들은 해당 요구사항을 토대로 개발을 진행해야한다. 

## 요구사항 
1. 사용자는 돈이 없으면 송금할 수 없다.
2. 동일한 송금은 두번 요청할 수 없다.
3. 송금 후에는 계좌잔액을 노출해야 한다.
4. 송금 내역은 5개월동안 보장되고 그 이후의 내역은 삭제되어야 한다.


## 도메인 
- Member ( 유저 )
  - id 
  - name 
  - createdAt

- remittance ( 송금 )
   - id 
   - senderId 
   - receiverId 
   - money 
   - createdAt

- Money ( 금액 )
  - money

- remittanceHistory ( 송금 내역 )
  - id 
  - remittances
  - createdAt
