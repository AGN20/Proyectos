# -*- coding: utf-8 -*-
from odoo import models, fields, api
from odoo.exceptions import ValidationError

#Definimos modelo Socios comic
class SociosComic(models.Model):

    #Nombre y descripcion del modelo
    _name = 'socios.comic'
    #Hereda de "base.archive" (el modelo abstracto creado antes)
    _inherit = ['base.archive']

    _description = 'Comic de socios'

    #Parametros de ordenacion por defecto
    _order = 'apellido desc, nombre'

    #ATRIBUTOS

    #PARA CUANDO NO HAY UN ATRIBUTO LLAMADO NAME PARA MOSTRAR NOMBRE DE UN REGISTRO
    # https://www.odoo.com/es_ES/forum/ayuda-1/how-defined-display-name-in-custom-many2one-91657
    
    #Indicamos que atributo sera el que se usara para mostrar nombre.
    #Por defecto es "name", pero si no hay un atributo que se llama name, aqui lo indicamos
    #Aqui indicamos que se use el atributo "nombre"
    _rec_name = 'nombre'
    #Atributo nombre
    nombre = fields.Char('Nombre', required=True)
    #Atributo apellido
    apellido = fields.Char('Apellido', required=True)
    #Atributo identificador
    id = fields.Char('id', required=True, index=True)
    #Atributo comics prestados
    comicPrestado = fields.Many2many("prestados.comic")

    #Constraints de SQL del modelo
    #Util cuando la constraint se puede definir con sintaxis SQL
    _sql_constraints = [
        ('name_uniq', 'UNIQUE (id)', 'El id de Socio debe ser único.'),
    ]


