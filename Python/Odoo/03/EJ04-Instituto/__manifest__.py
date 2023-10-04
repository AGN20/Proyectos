# -*- coding: utf-8 -*-
{
    'name': "Instituto",
    'summary': """
    Manejo instituto""",
    'description': """
    Sistema de gestion de modulos del instituto
    """,
    'author': "Adrián González",
    'application': True,
    'category': 'Productivity',
    'version': '0.1',
    'depends': ['base'],
    'data': [
        'security/groups.xml',
        'security/ir.model.access.csv',
        'views/instituto_ciclo_formativo.xml',
        'views/instituto_modulo.xml',
        'views/instituto_alumno.xml',
        'views/instituto_profesor.xml',
    ]
}