# Question 3 References

## Code References
- Oracle Java Documentation on Design Patterns: https://docs.oracle.com/javase/tutorial/java/IandI/
- Strategy Pattern in Java: https://refactoring.guru/design-patterns/strategy/java
- Composition vs Inheritance: https://www.geeksforgeeks.org/composition-vs-inheritance-in-java/

## AI Assistant Interactions
Me:
How can I explain that the current Car class structure can’t handle changing trim levels at runtime?
ChatGPT:
You could explain that inheritance in Java is fixed at compile time, so a Base car cannot become a Sports car dynamically. Also, creating a new object would break references and lose state.

Me:
Okay, and how do I describe using composition to allow dynamic trim changes?
ChatGPT:
Mention that you can make TrimLevel an interface and let Car hold a TrimLevel instance. This way, the trim can be updated at runtime while the Car object keeps its state. Highlight benefits like flexibility, object continuity, and adherence to the Open/Closed Principle.
