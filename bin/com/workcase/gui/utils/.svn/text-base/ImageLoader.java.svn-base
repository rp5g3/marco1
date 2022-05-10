/*
 * Created on Mar 3, 2005
 */
package com.workcase.gui.utils;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

/**
 * Carrega as imagens
 * TODO as vezes uma imagem eh carregada muitas vezes em uma aplicacao, nao sei exatamente
 * como funciona esse 'carregar', mas sera que ele busca todas as vezes do disco? Se sim, 
 * podemos implementar aqui um tipo de buffer de imagens com as ja carregadas para nao ter
 * todo esse acesso no disco
 * @author frodrigues
 */
public class ImageLoader {

    private static ImageLoader instance = null;
    
    protected ImageLoader() {
        
    }
    
    public static ImageLoader getInstance() {
        if (instance == null)
            instance = new ImageLoader();

        return instance;
    }
    
    /**
     * Retorna a imagem do caminho passado, nulo se nao encontrar
     * @param path
     * @return
     */
    public Image getImage(String path) {        
        URL imageURL = getClass().getResource(path);
        if (imageURL == null)
            return null;
        
        return new ImageIcon(imageURL).getImage();         
    }
}
