from sqlalchemy import Column, Integer, String
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.sql.expression import null
from . import sesionDB

class Alumne(sesionDB.Base):
    id = Column(Integer, primary_key=True)
    nombre = Column(String, nullable=False)
    clase = Column(String, nullable=False)
    años = Column(Integer, nullable=False)
    correo = Column(String)
    contacto = Column(Integer, nullable=False)

    def __init__(self, nombre, clase, años, correo, contacto):
        self.nombre = nombre
        self.clase = clase
        self.años = años
        self.correo = correo
        self.contacto = contacto