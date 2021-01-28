/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MechApp.controlador;

/**
 *
 * @author EdSar
 */
public class MecanicaMateriales {
    // Atributos
    
    // Métodos
    
    public static double[] esfuerzosInclinados (double esfuerzos[], double theta){
    /* Función que calcula los esfuerzos planos en un elemento con inclinación
    
    Argumentos: 
        esfuerzos: arreglo que contiene los esfueroz sigma_x, sigma_y y tau_xy
        theta : ángulo de inclinación del elemento infinitesimal
    Salida:
        Arreglo = [sigma_x1, sigma_y1, tau_x1y1, sigma_1, sigma_2]
    */
        double sigma_x, sigma_y, tau_xy;
        sigma_x = esfuerzos[0];
        sigma_y = esfuerzos[1];
        tau_xy = esfuerzos[2];
        theta = theta * Math.PI/180; // [grados --> rad]

        // Cálculo de esfuerzos en ejes y planos inclinados
        double sigma_x1 = ((sigma_x + sigma_y)/2) + (((sigma_x - sigma_y)/2) * Math.cos(2*theta))
                + (tau_xy * Math.sin(2*theta));
        double sigma_y1 = ((sigma_x + sigma_y)/2) - (((sigma_x - sigma_y)/2) * Math.cos(2*theta))
                - (tau_xy * Math.sin(2*theta));
        double tau_x1y1 = (tau_xy * Math.cos(2*theta)) - (((sigma_x - sigma_y)/2) * Math.sin(2*theta));
        
        // Esfuerzos principales
        double sigma_1 = ((sigma_x+sigma_y)/2) + 
                Math.sqrt((Math.pow((sigma_x-sigma_y)/2, 2))+(Math.pow(tau_xy, 2)));
        double sigma_2 = ((sigma_x+sigma_y)/2) - 
                Math.sqrt((Math.pow((sigma_x-sigma_y)/2, 2))+(Math.pow(tau_xy, 2)));
        
        // Ángulos principales
        double dostheta_p = Math.atan(2*tau_xy/(sigma_x - sigma_y));
        if (dostheta_p < 0) {
            dostheta_p = 2*Math.PI + dostheta_p;  
        };
        double dostheta_p1 = dostheta_p;
        double dostheta_p2 = dostheta_p + Math.PI;
        if (dostheta_p <= Math.PI) {
            dostheta_p2 = dostheta_p + Math.PI;
        } else {
            dostheta_p2 = dostheta_p - Math.PI;
        };
        
        double sigma_x1aux = ((sigma_x + sigma_y)/2) + (((sigma_x - sigma_y)/2) * Math.cos(dostheta_p1))
                + (tau_xy * Math.sin(dostheta_p1));
        double theta_p1 = dostheta_p1/2;
        double theta_p2 = dostheta_p2/2;
        if (sigma_x1aux == sigma_1) {
            theta_p1 = dostheta_p1/2;
            theta_p2 = dostheta_p2/2; 
        } else if (sigma_x1aux == sigma_2) {
            theta_p1 = (dostheta_p2)/2;
            theta_p2 = (dostheta_p1)/2;
        };
        theta_p1 = theta_p1 * 180/Math.PI;
        theta_p2 = theta_p2 * 180/Math.PI;
     
        double[] esfuerzosInclinado = 
            {sigma_x1, sigma_y1, tau_x1y1, sigma_1, sigma_2, theta_p1 , theta_p2};
        return esfuerzosInclinado;
    };
}
