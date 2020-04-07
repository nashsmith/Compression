## Authors:
- Konny Brown
- Nash Smith (1277758)


## Usage

All programs output a file for marking purposes (i.e. to check phrase numbers etc.)

```
Compressing:  java LZWencode "filename"
- This will encode the file into an encoded file

Bitpacking: java LZWpack
- Will automatically bitpack the file made by LZWencode into a packed file

Bitunpacking: java LZWunpack
- Will automatically unpack the file made by LZWpack into an unpacked file (should match encoded file)

Decompressing: java LZWdecode
- Will automatically decode the file made by LZWunpack into a file that matches the original
