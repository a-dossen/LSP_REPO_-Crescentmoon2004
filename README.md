# LSP Assignment #2 Submission 

CSCI 363/540 – Assignment 1: CSV ETL Pipeline in Java
======================================================

How to Run
----------
1. From the project root (where `src/` and `data/` are located):

   javac -d out src/org/howard/edu/lsp/assignment2/ETLPipeline.java
   java -cp out org.howard.edu.lsp.assignment2.ETLPipeline

2. Input file:  data/products.csv
3. Output file: data/transformed_products.csv


Assumptions
-----------
- Input CSV uses commas as the delimiter and does not contain quoted strings or commas inside fields.
- The first row is always a header.
- If a row has missing or invalid data, it is skipped with a warning instead of crashing the program.
- If the input file is missing, the program prints an error message and exits gracefully.
- If the input file only has a header row, the output file will contain just the header row.


Design Notes
------------
The program is organized into clear Extract, Transform, and Load phases:

- Extract: Read `products.csv` into simple Product objects.
- Transform: Apply transformations in the required order:
    1. Convert product names to uppercase.
    2. Apply a 10% discount to Electronics and round to 2 decimals (HALF_UP).
    3. If the discounted price is greater than $500 and the original category was Electronics,
       recategorize as "Premium Electronics".
    4. Assign a PriceRange (Low, Medium, High, Premium) based on the final price.
- Load: Write the transformed rows to `transformed_products.csv` with a header.

BigDecimal with RoundingMode.HALF_UP is used to ensure correct financial rounding.
Helper methods (transform, computePriceRange, writeOutput, etc.) keep the code clean and modular.


Testing Strategy
----------------
- Case A (Normal Input): Ran the program with the provided sample `products.csv` and verified
  output matches the golden file (names uppercase, discounts applied, categories updated, price ranges correct).
- Case B (Empty Input): Used a file with only the header; output contained just the header row.
- Case C (Missing Input File): Deleted the input file; program printed a clear error and exited.
- Edge Cases: Added blank lines and malformed numbers; those rows were skipped with warnings as expected.


AI Usage
--------
I used ChatGPT to brainstorm how to break the problem into steps and to double-check my rounding approach.

Example prompt I asked:
"How should I structure an ETL pipeline in Java that reads a CSV, applies transformations, and writes a new file?"

Excerpt from the AI’s response:
"It’s a good idea to separate the logic into extract, transform, and load methods, and to use BigDecimal with
RoundingMode.HALF_UP when you need consistent two-decimal rounding."

How I used it:
I liked the idea of splitting the program into three phases, which matched the assignment requirements,
and I kept the BigDecimal suggestion for rounding. I still wrote and organized the code myself and added
error handling for empty and missing input cases.


External Sources
----------------
- Oracle BigDecimal documentation: https://docs.oracle.com/javase/8/docs/api/java/math/BigDecimal.html
  Used to confirm how RoundingMode.HALF_UP works and adapted it to ensure prices always show two decimals.
