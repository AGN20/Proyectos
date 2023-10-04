from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker
from sqlalchemy.ext.declarative import declarative_base

#Creamos la BD y la sesion
engine = create_engine('sqlite:///scuela.sqlite')
#Creamos y iniciamos la sesion
Session = sessionmaker(bind=engine)
session = Session()

#Clase que con el metodo 'declarative_base()' realizara el mapeo
Base = declarative_base()