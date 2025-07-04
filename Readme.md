# Assumptions

- **Products** are concrete instances of classes, as this is a simulation for the customer side only.
- **Shipping Service** has its own attribute (**Cost per Kg**) which is used for calculation.
- **Validations** (checking expiry and quantity) occur both at the Cart and Checkout stages to ensure mistakes are caught early and to provide fast feedback for customers from the cart info page.