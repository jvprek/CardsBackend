# Payment Processor

## POST /payproc/banking/cards/create
Person requests issue of a new card, through the bank
### Input 
- name
- limit
- income
### Output 
- status `SUCCESS | REJECTED`

## POST /payproc/banking/activate
Card Owner activates new or replaced card,, through the bank (ATM, helpdesk etc)
### Input 
- PAN
- time
### Output 
HTTP status

## POST /payproc/banking/payments
Core banking notifies Payment processor for a new Customer Payment
### Input 
- accountId
- time
### Output 
HTTP status

## POST /payproc/merchant/transactions
Card Owner Initiates Transaction with a merchant
### Input 
- PAN
- Amount
- Merchant
- time
### Output
- tx-id
- Amount
- Merchant
- time
- status `SUCCESS | REJECTED`

## PUT /payproc/merchant/transactions/{tx-id}/completion
Merchant completes  a transaction 
### Input
- tx-id
### Output
HTTP status

## GET /payproc/customer/transactions/{accountId}/pending
Pending transactions for a credit card
### Input 
- accountId
### Output
- tx-id
- time
- Amount
- Merchant

## GET /payproc/customer/transactions/{accountId}/complete
Completed transactions for a credit card
### Input 
- accountId
### Output
- tx-id
- time
- Amount
- Merchant

# Core Banking

## POST /core-banking/accounts/cards
Account creation for a new credit cards
### INPUT
- PAN
- Name
- Limit
### Output
- status `SUCCESS | REJECTED`
- accountId (if status == SUCCESS)

## POST /core-banking/accounts/{accountId}
Tx Authorization
### Output
- accountId
- pan
- name
- balance
- limit
- status  `IN_PROGRESS | ACTIVE | SUSPENDED`

## POST /core-banking/payments/authorizations
Tx Authorization
### Input 
- AccountId
- Amount
- time
### Output
- tx-id
- status `SUCCESS | REJECTED`
- time

## POST /core-banking/payments/credits
Card account is credited either from a cards owner payment, transaction refund, or cancelation
### Input 
- AccountId
- Amount
- time
- type `PAYMENT | TX_CANCELLATION | REFUND`
### Output
HTTP status

## GET /core-banking/payments/cards/{accountId}
Credit Card Payment History
### Input 
- accountId
### Output
 Array of :
 - dateTime 
 - amount

# POST /core-banking/user-requests/cards
Core Domain has received a user request
### Input 
- AccountId
- time
- type `LOST_OR_STOLLEN | REPLACE`
### Output
HTTP status

## GET /core-banking/payments/user-requests/cards
Credit Card Payment History
### Input 
- accountId
### Output
 Array of :
    - AccountId
    - timeCreated:
    - type `LOST_OR_STOLLEN | REPLACE`
    - status `PENDING | COMPLETED`
    - timeCompleted: (null if status == PENDING)


