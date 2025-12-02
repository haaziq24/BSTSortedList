# BST Sorted List Implementation

A Java implementation of a Sorted List Abstract Data Type (ADT) using a Binary Search Tree as the underlying data structure.

## Overview

This project implements a sorted list that maintains elements in ascending order automatically. By leveraging the properties of a Binary Search Tree (BST), insertions and searches are efficient while keeping the list sorted at all times.

## Features

- **Automatic Sorting**: Elements are maintained in sorted order without manual sorting
- **Efficient Operations**: Utilizes BST properties for O(log n) average-case performance
- **Position-Based Access**: Retrieve elements by their position in the sorted order
- **Flexible Removal**: Remove elements by value or by position
- **Iterator Support**: In-order traversal provides sorted element access

## Project Structure

```
BSTSortedList/
├── BSTSortedList.java          # Main sorted list implementation
├── SortedListInterface.java    # Interface defining sorted list operations
├── Driver.java                 # Test program with sample usage
├── TreePackage/                # Binary Search Tree implementation
└── StackAndQueuePackage/       # Supporting data structures
```

## Implementation Details

### Key Components

- **BSTSortedList**: Implements the sorted list using a Binary Search Tree
- **Binary Search Tree**: Maintains elements in sorted order based on comparable values
- **InorderIterator**: Provides sorted traversal of list elements

### Supported Operations

| Operation | Description | Complexity |
|-----------|-------------|------------|
| `add(T entry)` | Adds element in sorted position | O(log n) avg |
| `remove(T entry)` | Removes specific element | O(log n) avg |
| `remove(int position)` | Removes element at position | O(n) |
| `getEntry(int position)` | Retrieves element at position | O(n) |
| `getPosition(T entry)` | Finds position of element | O(n) |
| `contains(T entry)` | Checks if element exists | O(log n) avg |
| `getLength()` | Returns number of elements | O(1) |
| `isEmpty()` | Checks if list is empty | O(1) |
| `clear()` | Removes all elements | O(1) |
| `toArray()` | Converts to array in sorted order | O(n) |

## Usage Example

```java
// Create a new sorted list
BSTSortedList<Integer> list = new BSTSortedList<>();

// Add elements (they'll be sorted automatically)
list.add(45);
list.add(15);
list.add(85);
list.add(25);

// Get element at position 2 (returns 25)
Integer value = list.getEntry(2);

// Check if list contains a value
boolean exists = list.contains(45); // returns true

// Get position of an element
int position = list.getPosition(85); // returns 4

// Remove element by value
list.remove(45);

// Remove element by position
list.remove(2);

// Convert to array
Integer[] array = list.toArray();
```

## Running the Tests

The `Driver.java` file contains comprehensive tests for all operations:

```bash
javac BSTSortedList.java Driver.java
java Driver
```

Expected output includes tests for:
- Adding elements and maintaining sorted order
- Retrieving elements by position
- Finding positions of elements
- Checking containment
- Removing elements by value and position
- Clearing the list
- Working with empty lists

## Design Decisions

### Why Binary Search Tree?

1. **Natural Sorting**: BST insertion maintains sorted order automatically
2. **Efficient Search**: O(log n) average-case lookup performance
3. **InorderIterator**: Provides sorted traversal without additional sorting overhead

### Trade-offs

- **Position-based operations**: O(n) complexity due to tree traversal requirement
- **Balanced vs Unbalanced**: Performance degrades to O(n) in worst-case (unbalanced tree)
- **Memory**: Each element requires tree node overhead (left/right pointers)

## Technical Requirements

- **Java Version**: Java 8 or higher
- **Dependencies**: None (uses only Java standard library)
- **IDE**: Developed with BlueJ, compatible with any Java IDE

## Academic Context

This project was completed as a programming assignment to demonstrate:
- Understanding of Binary Search Tree data structures
- Implementation of Abstract Data Types (ADTs)
- Efficient use of existing data structure methods
- Code organization and documentation practices

## Author

Haaziq Abbasi

## License

This project is for educational purposes.
