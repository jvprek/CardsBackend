## Successfull User Card transaction 
Given a credit cards owner
And a credit card with the related bank account
And a merchant
And an order for a specific amount
And the payment processor
And the core banking
When the credit cards owner places the order to the merchant
Then the merchant will ask for approval for the order from the  payment processor
And the payment processor will ask for approval from core domain for the amount and the credit card
And the core domain  successfully checks that the amount is smaller than the  credit card account balance.
And the core domain approves the transaction and debits the credit card account.
And the payment processor stores the transactions and marks it as 'Pending'.
And the merchant  is informed about the transaction is approved
And the card owner  is informed about the transaction is approved.

## Rejected User Card transaction 
Given a credit cards owner
And a credit card with the related bank account
And a merchant
And an order for a specific amount
And the payment processor
And the core banking
When the credit cards owner places the order to the merchant
Then the merchant will ask for approval for the order from the  payment processor
And the payment processor will ask for approval from core domain for the amount and the credit card
And the core domain  finds that  that the amount is bigger than the  credit card account balance.
And the core domain rejects the transaction.
And the payment processor stores the transactions and marks it as 'Rejected'.
And the merchant  is informed about the transaction is rejected
And the card owner  is informed about the transaction is rejected.

## Pending Transaction Completion
Given a pending transaction
And a merchant
And the payment processor
And the core banking
When the credit cards owner places the order to the merchant
Then the merchant will ask for approval for the order from the  payment processor
And the payment processor will ask for approval from core domain for the amount and the credit card
And the core domain rejects the transactions.
And the merchant and is informed about the transaction is rejected.
And the card owner and is informed about the transaction is rejected.