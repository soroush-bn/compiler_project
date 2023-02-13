# Compiler Project

This is a compiler for a simple programming language built using Python. The compiler performs lexical analysis, parsing, and code generation for a subset of the C programming language.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Introduction

This project is a basic implementation of a compiler for a simple programming language that supports variable declarations, arithmetic expressions, conditional statements, and loops. The compiler takes source code written in this language and produces assembly code that can be executed on a computer.

## Features

The compiler has the following features:

- Lexical analysis: The compiler analyzes the input source code and generates a stream of tokens that represent the different parts of the code, such as keywords, operators, and identifiers.
- Parsing: The compiler uses a context-free grammar to parse the token stream and build an abstract syntax tree (AST) that represents the structure of the program.
- Code generation: The compiler generates assembly code for the program by traversing the AST and translating each node into the corresponding assembly instructions.

## Usage

To use the compiler, simply run the `compiler.py` script with the path to the input file as an argument:




The output of the compiler will be written to a file with the same name as the input file, but with a `.asm` extension.

## Contributing

Contributions to the project are welcome. If you find a bug or have an idea for a new feature, please open an issue on the project's GitHub page. If you would like to contribute code, please fork the repository and submit a pull request with your changes.


