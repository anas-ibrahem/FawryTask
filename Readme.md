# UML
![image](https://github.com/user-attachments/assets/69898778-e304-49da-ba8e-9ba59b456e2c)

# Design Notes
- Using HashMap in Cart to provide easy and fast access and updates.
- Using Product Validator to avoid rewriting validation functions (Used in both Cart and Checkout Service to proved initial and last Checks on Qunatity and ExpiryDates).

# Assumptions

- **Products** are concrete instances of classes ( Categories like Food and Electrcial Devices ), as this is a simulation for the customer side only.
- **Shipping Service** has its own attribute (**Cost per Kg**) which is used for calculation.
- **Validations** (checking expiry and quantity) occur both at the Cart and Checkout stages to ensure mistakes are caught early and to provide fast feedback for customers from the cart info page.
