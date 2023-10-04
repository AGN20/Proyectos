# -*- coding: utf-8 -*-
from odoo import models, fields, api
from odoo.exceptions import ValidationError
from datetime import date, timedelta

class reparto(models.Model):
    #Nombre del model
    _name = 'reparto'
    _description = 'Modelo almacenar repartos'
    #Queremos que ordene los repartidores por apellido
    _order = 'fecha_entrega, urgencia'
    #Indicamos que si en otra vista se ve, muestre el codigo de repartidor
    _rec_name = "c_reparto"

    #Elementos
    c_reparto = fields.Char('Codigo reparto', required=True, index=True)

    fecha_inicio = fields.Date('Fecha inicio')
    fecha_retorno = fields.Date('Fecha retorno')
    fecha_entrega = fields.Date('Fecha entrega', index=True)

    repartidor = fields.Many2one('empleado', 'Repartidor')

    vehiculo_reparto = fields.Many2one('vehiculo', 'Vehiculo de reparto')

    k_reparto = fields.Float('Kilometros de reparto')

    peso = fields.Float('Peso en kg del paquete')
    volumen_paquete = fields.Float('Volumen del paquete')

    observaciones = fields.Text('Observaciones del paquete')

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
        'Estado de la entrega', default="1")

    cliente_emisor = fields.Many2one('cliente', 'Cliente')

    receptor_nombre = fields.Char('Receptor del paquete', index=True)
    receptor_dni = fields.Char('DNI del receptor', index=True)

    #Campo que no sera visible, pero esta para que los empleados puedan ver que repartos les queda
    list_repartos = fields.Many2one('empleado')

    #Constraints de SQL del modelo
    _sql_constraints = [
        ('c_reparto_uniq', 'UNIQUE (c_reparto)', 'El codigo de reparto tiene que ser diferente.')
    ]

    #Controlamos que el conductor tenga carnet    
    @api.constrains('repartidor', 'vehiculo_reparto')
    def _check_repartidor(self):
        for record in self:
            if(record.repartidor.carnet == '4' and  record.vehiculo_reparto.tipo != '3'):
                raise ValidationError("El empleado no tiene carnet, no puede realizar el pedido")
            if(record.repartidor.carnet == '1' and  record.vehiculo_reparto.tipo != '1'):
                raise ValidationError("El empleado no tiene el carnet de ciclomotor, no puede realizar el pedido")
            if(record.repartidor.carnet == '2' and  record.vehiculo_reparto.tipo != '2'):
                raise ValidationError("El empleado no tiene el carnet de furgoneta, no puede realizar el pedido")

            #Comprobamos si el booleano es True no dejara que cojas al empleado
            if(record.repartidor.ocupado == True):
                raise ValidationError("El empleado se encuentra ya repartiendo")
            else:
                record.repartidor.ocupado = True

    #Controlamos que las bicicletas solo llegan asta 10km y las furgonetas no pueden ir a menos de 1km
    @api.constrains('vehiculo_reparto', 'k_reparto')
    def _check_vehiculo(self):
        for record in self:
            if(record.k_reparto > 10):
                if (record.vehiculo_reparto.tipo == '3'):
                    raise ValidationError("Una bicicleta no puede llevar paquetes a mas de 10km.")
            if (record.k_reparto < 1):
                if (record.vehiculo_reparto.tipo == '2'):
                    raise ValidationError("Una fugoneta no puede llevar paquetes a menos de 1km.")
    
    #Controlamos que los vehiculos utilizados, si es True no pueden cojer el vehiculo
    @api.constrains('vehiculo_reparto')
    def _check_en_uso(self):
        for record in self:
            if(record.vehiculo_reparto.ocupado == True):
                raise ValidationError("El vehiculo se encuentra ya repartiendo")
            else:
                record.vehiculo_reparto.ocupado = True


            

    