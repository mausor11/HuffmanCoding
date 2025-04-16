# 🗜️ Huffman Coding

This project implements **Huffman Coding** in Python – a classic lossless data compression algorithm that assigns variable-length codes to characters based on their frequencies.

## 🧠 What is Huffman Coding?

Huffman Coding is a greedy algorithm used for **data compression**. It ensures that:
- More frequent characters get shorter binary codes
- Less frequent characters get longer codes
- The result is a **prefix-free** binary encoding tree

## 📦 Features

- Builds a Huffman Tree from input text
- Generates Huffman codes for each character
- Encodes input text to a binary string
- Decodes binary string back to original text
- Works with files or plain strings

## 📘 Example

Original text:
```
huffman coding is cool
```

Encoded binary:
```
101011000111...
```

Decoded back:
```
huffman coding is cool
```

## 🛠 How to Run

```bash
python huffman.py
```

You can modify the `main()` function or pass your own input string to test compression and decompression.

## 📚 Applications

- File compression (e.g., ZIP, GZIP)
- Image compression (e.g., JPEG)
- Transmission of data in bandwidth-limited systems

## 📄 License

MIT License – feel free to use and adapt it.

---

> 🧪 This project was built as an exercise in algorithm design and string processing using binary trees.
