# Payment Processor Endpoints

## Merchant Interface

### POST /payproc/merchant/transactions
Card Owner real time  Transaction with a merchant
##### Input 
- PAN
- Amount
- Merchant
##### Output
- tx-id
- Amount
- Merchant
- time
- status `SUCCESS | REJECTED`

### PUT /payproc/merchant/transactions/{tx-id}/completion
Merchant completes  a transaction 
##### Input
- tx-id
- status `COMPLETE | CANCELLED`
##### Output
HTTP status

## Online Interface

### POST /payproc/online/transactions
Card Owner Online Transaction, needing owner's approval
##### Input 
- PAN
- Amount
- Merchant
##### Output
- tx-id

###  KAFKA PUT /payproc/online/transactions/
Card Owner confirms or rejects an Online Transaction 
##### Input 
- tx-id
- status `SUCCESS | REJECTED`- Amount
- time
##### Output
HTTP Status

## Banking Interface

### SOAP getPendingTransactions/{accountId}
Pending transactions for a credit card
##### Input 
- accountId
##### Output
- tx-id
- time
- Amount
- Merchant


### GET /core-banking/accounts/{accountId}
Information about a Credit Card Account
##### Output
- accountId
- pan
- name
- balance
- limit
- status  `IN_PROGRESS | ACTIVE | SUSPENDED`

### POST /core-banking/payments/debit
Tx Authorization, account is debited
##### Input 
- AccountId
- Amount
- time
##### Output
- tx-id
- status `SUCCESS | REJECTED`
- time

### POST /core-banking/payments/credit
Card account is credited either from a cards owner payment, transaction refund, or cancelation
##### Input 
- AccountId
- Amount
- time
- type `PAYMENT | TX_CANCELLATION | REFUND`
##### Output
HTTP status

### GET /core-banking/payments/cards/{accountId}
Credit Card Payment History
##### Input 
- accountId

##### Output
 Array of :
 - dateTime 
 - amount

# POST /core-banking/user-requests/cards
Core Domain has received a user request
##### Input 
- AccountId
- time
- type `LOST_OR_STOLLEN | REPLACE`
##### Output
HTTP status

### GET /core-banking/payments/user-requests/cards/{accountId}/?
Credit Card Payment History
##### Input 
- accountId
##### Output
 Array of :
    - AccountId
    - timeCreated:
    - type `LOST_OR_STOLLEN | REPLACE`
    - status `PENDING | COMPLETED`
    - timeCompleted: (null if status == PENDING)


# Cards Domain Endpoints

### GET /cards/{accountId}/info

### GET /cards/{accountId}/transactions

### POST /cards/user-requests
User Request 
##### Input 
- AccountId
- time
- type `LOST_OR_STOLLEN | REPLACE`
##### Output
HTTP status

### GET /cards/{accountId}/pending-user-requests



