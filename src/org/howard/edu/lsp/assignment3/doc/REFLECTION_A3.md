# Assignment 3 Reflection

## Design Differences
Assignment 2 and Assignment 3 represent two different approaches to solving the same ETL pipeline problem. The key differences are:

1. **Code Organization**: 
   - Assignment 2: Single monolithic class containing all logic
   - Assignment 3: Multiple classes with clear, single responsibilities

2. **Data Encapsulation**:
   - Assignment 2: Product was a static inner class with public fields
   - Assignment 3: Product is a standalone class with private fields and public methods

3. **Separation of Concerns**:
   - Assignment 2: Mixed file I/O, business logic, and data representation
   - Assignment 3: Separate classes for reading (CSVReader), writing (CSVWriter), data (Product), and orchestration (ETLPipeline)

## Object-Oriented Improvements
The Assignment 3 implementation is more object-oriented in several ways:

1. **Better Encapsulation**: Data and methods that operate on that data are kept together. For example, the Product class now contains all product-related transformation logic.

2. **Single Responsibility Principle**: Each class has a clear, single purpose:
   - CSVReader: Handles file reading and parsing
   - CSVWriter: Manages file output
   - Product: Represents and transforms product data
   - ETLPipeline: Orchestrates the overall process

3. **Information Hiding**: Implementation details are hidden behind public interfaces, making the code more maintainable and reducing dependencies between components.

## Object-Oriented Concepts Used

1. **Objects and Classes**:
   - Created separate classes for different components (Product, CSVReader, CSVWriter)
   - Each class represents a clear concept in the domain

2. **Encapsulation**:
   - Private fields with public methods in Product class
   - Internal implementation details hidden in each class
   - Clear public interfaces for interaction

3. **Information Hiding**:
   - Implementation details of file I/O are hidden in CSVReader and CSVWriter
   - Product transformation logic is encapsulated within the Product class
   - Error handling is managed at appropriate levels

## Testing and Verification
To ensure Assignment 3 maintains the same functionality as Assignment 2:

1. **Input/Output Testing**:
   - Used the same test input files
   - Compared output files between both versions
   - Verified header handling and CSV formatting

2. **Error Cases**:
   - Tested with missing input file
   - Verified empty input file handling
   - Checked malformed row handling

3. **Data Transformation**:
   - Verified product name capitalization
   - Confirmed electronics discount calculation
   - Checked price range categorization
   - Validated Premium Electronics recategorization

All tests confirmed that Assignment 3 produces identical results to Assignment 2 while providing a more maintainable and object-oriented structure.
