# -*- coding: utf-8 -*-
from odoo import http
from odoo.http import request
from PIL import Image
from io import BytesIO

import base64
import io
import random

#Web Controller
class GenerarImagen(http.Controller):

    #Para entrar a nuestro web controler tenemos que en el navegador la siguiente ruta:  http://localhost:8069/crear?altura=altura_deseada&anchura=anchura_deseada

    altura = 0
    anchura = 0

    @http.route('/crear',auth='public',website=True)
    def generarImagen(self,**kw):

        #Cogemos la anchura y la altura que se nos pasa
        self.altura = int(request.params['altura'])
        self.anchura = int(request.params['anchura'])

        #llamamos a la funcion que nos genera la imagen
        imagen = self.crearImagen()

        #Creamos el buffer
        buffer_io = BytesIO()

        #Guardamos la imagen en el buffer y le ponemos el tipo de imagen que es
        imagen.save(buffer_io, "PNG")

        #Pasamos a base 64
        data_uri = base64.b64encode(buffer_io.getvalue()).decode('utf-8')

        #Introducimos la imagen como si fuera un html
        return "<img src='data:image/png;base64,{0}'>".format(data_uri)

    """Crearemos la imagen para ello generaremos la imagen y despues cambiaremos por pixeles los colores"""
    def crearImagen(self):

        #Creamos la imagen (los colores R G B que le tenemos que pasar, de momento no son importantes asi que los voy a dejar a 0, mas adelante los modificaremos)
        imagen = Image.new("RGB",(self.anchura,self.altura),(255,255,255))
        pixeles = imagen.load()

        #Ahora modificamos los colores, para ello por cada pixel en altura y por cada pixel en anchura generaremos un color aleatorio(recuerda 3 numeros aleatorios R G B)
        for x in range(self.anchura):
            for y in range(self.altura):
                #Generamos numeros aleatorios entre 0 y 255 (255 es el maximo de rango en los colores en RGB)
                r = random.randrange(0,255)
                g = random.randrange(0,255)
                b = random.randrange(0,255)

                #Metemos el color al pixel de la imagen
                pixeles[x,y]=(r,g,b)
        
        #devolbemos la imagen
        return imagen

