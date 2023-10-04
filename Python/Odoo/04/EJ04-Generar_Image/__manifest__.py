# -*- coding: utf-8 -*-
{
    'name': "Generardor de imagenes aleatorias",  # Titulo del módulo
    'summary': "A partir de un numero aleatorio genera una imagen",  # Resumen de la funcionaliadad
    'description': """
    A partir de una llamada web, genera una imagen
    ==============
    """,  

    #Indicamos que es una aplicación
    'application': True,
    'author': "Adrián González",
    'website': "http://apuntesfpinformatica.es",
    'category': 'Tools',
    'version': '0.1',
    'depends': ['base'],

    #IMPORTANTE: Si estais usando Docker Compose, debeis instalar la dependencia:
    #docker-compose exec web bash
    #y tras ello pip3 install python-barcode y luego pip3 install python-barcode[images]

    'data': [
    ],
    # Fichero con data de demo si se inicializa la base de datos con "demo data" (No incluido en ejemplo)
    # 'demo': [
    #     'demo.xml'
    # ],
}
