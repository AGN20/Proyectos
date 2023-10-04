# -*- coding: utf-8 -*-
from odoo import models, fields, api
from odoo.exceptions import ValidationError

#Definimos modelo Prestados comic
class PrestadosComic(models.Model):

    #Nombre y descripcion del modelo
    _name = 'prestados.comic'
    #Hereda de "base.archive" (el modelo abstracto creado antes)
    _inherit = ['base.archive']

    _description = 'Comic de Prestamos'

    #Parametros de ordenacion por defecto
    _order = 'id desc'

    #ATRIBUTOS

    #PARA CUANDO NO HAY UN ATRIBUTO LLAMADO NAME PARA MOSTRAR NOMBRE DE UN REGISTRO
    # https://www.odoo.com/es_ES/forum/ayuda-1/how-defined-display-name-in-custom-many2one-91657
    
    _rec_name = 'id'

    #Indicamos que atributo sera el que se usara para mostrar nombre.
    #Por defecto es "name", pero si no hay un atributo que se llama name, aqui lo indicamos
    #Atributo identificador
    id = fields.Char('id', required=True, index=True)
    #Atributo comics prestados
    comicPrestado = fields.Many2many("biblioteca.comic")
    #Atributo al socio prestados
    socioPrestado = fields.Many2many("socios.comic")
    #Atributo Fecha de prestamo
    fecha_prestamo = fields.Date('Fecha prestamo')
    #Atributo de debolucion del prestamo
    fecha_debolucion = fields.Date('Fecha debolucion')

    #Constraints de SQL del modelo
    #Util cuando la constraint se puede definir con sintaxis SQL
    _sql_constraints = [
        ('name_uniq', 'UNIQUE (id)', 'El id de Socio debe ser único.'),
    ]

    @api.constrains('fecha_debolucion', 'fecha_prestamo')
    def _check_release_date(self):
        # Recorremos el modelo
        for record in self:
            #La data de préstec no pot ser posterior al dia de hui.
            if  record.fecha_prestamo > fields.Date.today():
                #Si procede, lanzamos una excepcion
                raise models.ValidationError('La fecha de prestamo deve ser anterior a la de hoy')
        
            #La data prevista de tornada no pot ser anterior al dia de hui.
            if  record.fecha_debolucion < fields.Date.today():
                #Si procede, lanzamos una excepcion
                raise models.ValidationError('La fecha de devolucion deve ser superior a la de hoy')

                
