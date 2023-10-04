# adds more image processing capabilities
from PIL import Image, ImageEnhance
import pytesseract

# aisgnamos la ruta de la imagen a img
#img = Image.open("/home/adrian/Documentos/Python/U05EJ02/Ej12/prueba1.png")
img = Image.open("/home/adrian/Documentos/Python/U05EJ02/Ej12/prueba2.png")
# adding some sharpness and contrast to the image 
enhancer1 = ImageEnhance.Sharpness(img)
enhancer2 = ImageEnhance.Contrast(img)
img_edit = enhancer1.enhance(20.0)
img_edit = enhancer2.enhance(1.5)


# convertimos la imagen a texto y lo guardamos
result = pytesseract.image_to_string(img_edit)

# Escribimos un texto con el resultado de la imagen convertida
#with open("/home/adrian/Documentos/Python/U05EJ02/Ej12/text_result1.txt", mode ="w") as file:
with open("/home/adrian/Documentos/Python/U05EJ02/Ej12/text_result2.txt", mode ="w") as file:
    file.write(result)
    print("ready!")