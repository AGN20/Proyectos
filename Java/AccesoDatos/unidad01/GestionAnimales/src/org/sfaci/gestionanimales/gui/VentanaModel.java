package org.sfaci.gestionanimales.gui;

import org.sfaci.gestionanimales.base.Animal;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static javax.imageio.ImageIO.read;

/**
 * Modelo para la ventana
 * @author Santiago Faci
 * @curso 2015-2016
 */
public class VentanaModel {

    private ArrayList<Animal> listaAnimales;
    private int posicion;

    public VentanaModel() {
        listaAnimales = new ArrayList<>();
        posicion = 0;
    }

    /**
     * Guarda un animal en la lista
     * @param animal
     */
    public void guardar(Animal animal) {

        listaAnimales.add(animal);
        posicion++;
    }

    /**
     * Modifica los datos del animal actual
     * @param animalModificado
     */
    public void modificar(Animal animalModificado) {//MODIFICAR

        Animal animal = listaAnimales.get(posicion);
        animal.setNombre(animalModificado.getNombre());
        animal.setCaracteristicas(animalModificado.getCaracteristicas());
        animal.setRaza(animalModificado.getRaza());
        animal.setPeso(animalModificado.getPeso());
    }

    /**
     * Elimina el animal actual
     */
    public void eliminar() {
        listaAnimales.remove(posicion);
    }//ELIMINAR

    public Animal getActual() {
        return listaAnimales.get(posicion);
    }

    /**
     * Busca un animal en la lista
     * @param nombre El nombre del animal
     * @return El animal o null si no se ha encontrado nada
     */
    public Animal buscar(String nombre) {
        for (Animal animal : listaAnimales) {
            if (animal.getNombre().equals(nombre)) {
                return animal;
            }
        }

        return null;
    }

    /**
     * Obtiene el animal que está en primera posición en la lista
     * @return
     */
    public Animal getPrimero() {

        posicion = 0;
        return listaAnimales.get(posicion);
    }

    /**
     * Obtiene el animal que está en la posición anterior a la actual
     * @return
     */
    public Animal getAnterior() {

        if (posicion == 0)
            return null;

        posicion--;
        return listaAnimales.get(posicion);
    }

    /**
     * Obtiene el animal que está en la posición siguiente a la actual
     * @return
     */
    public Animal getSiguiente() {

        if (posicion == listaAnimales.size() - 1)
            return null;

        posicion++;
        return listaAnimales.get(posicion);
    }

    /**
     * Obtiene el animal que está en la última posición de la lista
     * @return
     */
    public Animal getUltimo() {

        posicion = listaAnimales.size() - 1;
        return listaAnimales.get(posicion);
    }

    public void guardarXML(){
        try {

            JFileChooser guardar = new JFileChooser();
            guardar.showSaveDialog(null);
            guardar.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            File ruta = guardar.getSelectedFile();


            if(!ruta.exists()){
                ruta.createNewFile();
            }

            DocumentBuilderFactory docF = DocumentBuilderFactory.newInstance();
            DocumentBuilder docb = docF.newDocumentBuilder();
            DOMImplementation imp = docb.getDOMImplementation();
            Document doc = imp.createDocument(null, "ListaAnimales", null);

            Element animales = doc.createElement("Animales");

            doc.getDocumentElement().appendChild(animales);

            for(int x = 0; x < listaAnimales.size(); x++) {
                Element animal = doc.createElement("Animal");
                Element nom = doc.createElement("Nombre");
                Text nomAnimal = doc.createTextNode(listaAnimales.get(x).getNombre());
                Element raza = doc.createElement("Raza");
                Text razaAnimal = doc.createTextNode(listaAnimales.get(x).getRaza());
                Element caracteristicas = doc.createElement("Caracteristicas");
                Text caracteristicasAnimal = doc.createTextNode(listaAnimales.get(x).getCaracteristicas());
                Element peso = doc.createElement("Peso");
                Text pesoAnimal = doc.createTextNode(String.valueOf(listaAnimales.get(x).getPeso()));

                animales.appendChild(animal);
                animal.appendChild(nom);
                nom.appendChild(nomAnimal);
                animal.appendChild(raza);
                raza.appendChild(razaAnimal);
                animal.appendChild(caracteristicas);
                caracteristicas.appendChild(caracteristicasAnimal);
                animal.appendChild(peso);
                peso.appendChild(pesoAnimal);


            }

            DOMSource fuente = new DOMSource(doc);
            Result resultado = new StreamResult(ruta);
            Transformer transformador = TransformerFactory.newInstance().newTransformer();
            transformador.transform(fuente, resultado);

        } catch (ParserConfigurationException | IOException | TransformerException e) {
            e.printStackTrace();
        }

    }

    public void cargarXML(){

        try {

            JFileChooser guardar = new JFileChooser();
            guardar.showSaveDialog(null);
            guardar.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            File ruta = guardar.getSelectedFile();

            DocumentBuilderFactory docf = DocumentBuilderFactory.newInstance();
            DocumentBuilder docb = docf.newDocumentBuilder();
            Document doc = docb.parse(ruta);

            String nombre = "";
            String raza = "";
            String caracteristicas = "";
            float peso = 0;


            NodeList animales = doc.getElementsByTagName("Animal");

            for (int i = 0; i < animales.getLength(); i++) {
                Node animals = animales.item(i);
                if (animals.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) animals;//Obtenemos los elementos del nodo
                    if (animals.getNodeType() == Node.ELEMENT_NODE) {
                        for (int x = 0; x < elemento.getChildNodes().getLength(); x++) {
                            String controler = elemento.getChildNodes().item(x).getNodeName();
                            if (!controler.equals("#text")) {

                                Element elem = doc.createElement(elemento.getChildNodes().item(x).getNodeName());
                                Text text = doc.createTextNode(elemento.getChildNodes().item(x).getTextContent()); //damos valor

                                if(elem.getTagName().equals("Nombre")){ nombre = text.getTextContent();}
                                else if(elem.getTagName().equals("Raza")){ raza = text.getTextContent();}
                                else if(elem.getTagName().equals("Caracteristicas")){ caracteristicas = text.getTextContent();}
                                else if(elem.getTagName().equals("Peso")){ peso = Float.parseFloat(text.getTextContent())  ;}

                            }

                        }
                    }
                }

                Animal animalNew = new Animal();
                animalNew.setNombre(nombre);
                animalNew.setRaza(raza);
                animalNew.setCaracteristicas(caracteristicas);
                animalNew.setPeso(peso);

                listaAnimales.add(animalNew);

            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

    }

}
