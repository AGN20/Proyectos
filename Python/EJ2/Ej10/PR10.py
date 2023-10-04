#import cv2 #cv2 no funciona
import os
from PIL import Image
from pyzbar.pyzbar import decode
  
# Make one method to decode the barcode
def BarcodeReader(image):
     
  if os.path.exists(image):

    # read the image in numpy array using cv2
    img =  image
      
    # Decode the barcode image
    detectedBarcodes = decode(img)
      
    # If not detected then print the message
    if not detectedBarcodes:
        print("Barcode Not Detected or your barcode is blank/corrupted!")
    else:
      
          # Traverse through all the detected barcodes in image
        for barcode in detectedBarcodes: 
          
            # Locate the barcode position in image
            (x, y, w, h) = barcode.rect
            
            # Put the rectangle in image using
            # cv2 to heighlight the barcode
            cv2.rectangle(img, (x-10, y-10),
                          (x + w+10, y + h+10),
                          (255, 0, 0), 2)
            
            if barcode.data!="":
              
            # Print the barcode data
                print(barcode.data)
                print(barcode.type)
                
  else:
    print("La ruta especificada no existe")

if __name__ == "__main__":
  # Take the image from user
    image= input("Ruta de la imagen a descodificar: ")
    BarcodeReader(image)