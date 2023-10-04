# -*- coding: utf-8 -*-
from odoo import models, fields


class RepartoWizard(models.TransientModel):
    _name = 'reparto.wizard'

    #Elementos del wizard
    c_reparto = fields.Char('Clave reparto', required=True)

    fecha_inicio = fields.Date('Fecha salida')
    fecha_retorno = fields.Date('Fecha retorno')
    fecha_entrega = fields.Date('Fecha entrega')

    repartidor = fields.Many2one('empleado', 'Repartidor')

    vehiculo_reparto = fields.Many2one('vehiculo', 'Vehiculo')

    k_reparto = fields.Float('Kilometros')

    peso = fields.Float('Peso paquete')
    volumen_paquete = fields.Float('Volumen paquete')

    observaciones = fields.Text('Observaciones')
    

    urgencia = fields.Selection(
        [('1', 'órganos humanos'),
        ('2', 'alimentos refrigerados'),
        ('3', 'alimentos'),
        ('4', 'alta prioridad'),
        ('4', 'baja prioridad')],
        'Urgencia', default="1")

    estado = fields.Selection(
        [('1', 'no ha salido'),
        ('2', 'en camino'),
        ('3', 'entregado')],
        'Estado de la entrega', default="1"
    )

    cliente_emisor = fields.Many2one('cliente', 'Cliente')
    
    receptor_nombre = fields.Char('Receptor del paquete')
    receptor_dni = fields.Char('DNI receptor')

    def add_reparto(self):

        repartoModel = self.env['reparto']
       
        for wiz in self:

            repartoModel.create({
                'c_reparto': wiz.c_reparto,
                'fecha_inicio': wiz.fecha_inicio,
                'fecha_retorno': wiz.fecha_retorno,
                'fecha_entrega': wiz.fecha_entrega,
                'repartidor': wiz.repartidor.id,
                'vehiculo_reparto': wiz.vehiculo_reparto.id,
                'k_reparto': wiz.k_reparto,
                'peso': wiz.peso,
                'volumen_paquete': wiz.volumen_paquete,
                'observaciones': wiz.observaciones,
                'urgencia': wiz.urgencia,
                'estado': wiz.estado,
                'cliente_emisor': wiz.cliente_emisor.id,
                'receptor_nombre': wiz.receptor_nombre,
                'receptor_nombre': wiz.receptor_nombre,
                'receptor_dni': wiz.receptor_dni,
            })