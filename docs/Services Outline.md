# Payment Processor
## POST /payproc/customers/cards/activate
Card Owner activates new or replaced card
### Input 
- PAN
- time
### Output 
HTTP status

## POST /payproc/customers/transactions
Card Owner Initiates Transaction
### Input 
- PAN
- Amount
- Merchant
- time
### Output
- tx-id
- status SUCCESS | REJECTED
- Amount
- Merchant
- time

# Core Banking
## GET /payproc/customers/pending-transactions/{accountId}
Pending transactions for a credit card
### Input 
- accountId
### Output
- tx-id
- time
- Amount
- Merchant



## POST /core-banking/cards/authorizations
Core Domain Tx Authorization
### Input 
- AccountId
- Amount
- time
### Output
- tx-id
- status SUCCESS | REJECTED
- time

## POST /core-banking/cards/accounts/{accountId}
Core Domain Tx Authorization
### Output
- accountId
- PAN
- Name
- Balance
- Limit
- Status  IN_PROGRESS | ACTIVE | SUSPENDED

