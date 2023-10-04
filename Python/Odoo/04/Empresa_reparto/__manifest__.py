# -*- coding: utf-8 -*-
{
    #Nombre del archivo
    'name': "Empresa Reparto",

    #Summary
    'summary': """
    Gestion de la empresa de repartos""",

    #Descripcion
    'decription': """
    modulo sencillo que gestiona todos los recursos de una empresa de reparto
    """,

    #Autor del modulo
    'author': "Adrián González Núñez",

    #Indicamos que es una aplicacion
    'application': True,

    #Indicamos en que apartado de Odoo esta y que version tiene
    'category': 'Productivity',
    'version': '0.1',

    #Modulos de los que depende la applicacion
    'depends': ['base'],

    #Datos que se van a acargar
    'data': [
        #fichero de seguridad
        'security/ir.model.access.csv',
        #Vista del informe
        'report/pendents.xml',
        #vistas y plantillas
        'views/empleado.xml',
        'views/vehiculo.xml',
        'views/cliente.xml',
        'views/reparto.xml',
        #Vista de wizard
        'wizard/reparto_wizard.xml'
    ]
}
