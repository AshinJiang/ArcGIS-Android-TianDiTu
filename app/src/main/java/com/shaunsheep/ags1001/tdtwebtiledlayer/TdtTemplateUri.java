package com.shaunsheep.ags1001.tdtwebtiledlayer;

/**
 * Created by jiang on 2017/7/3.
 */

public class TdtTemplateUri {

    public enum ServiceType {
        VEC_W, CVA_W, EVA_W, IMG_W, CIA_W, TER_W, CTA_W, VEC_C, CVA_C, EVA_C, IMG_C, CIA_C, TER_C, CTA_C
    }

    public enum LayerName {
        VECTOR, VECTOR_ANNOTATION_CHINESE, VECTOR_ANNOTATION_ENGLISH,
        IMAGE, IMAGE_ANNOTATION_CHINESE, IMAGE_ANNOTATION_ENGLISH,
        TERRAIN, TERRAIN_ANNOTATION_CHINESE
    }

    public enum TiledFormat{
        PNG,TILES,JPEG
    }

    public static String getTemplateUri(ServiceType serviceType, LayerName layerName, TiledFormat format) {
        String templateUri = "?service=wmts&request=gettile&version=1.0.0&"+
                "STYLE=default&tilematrix={level}&tilerow={row}&tilecol={col}&"+
                "layer=%s&tilematrixset=%s&format=%s";
        String baseUrl = "http://t0.tianditu.com/%s/wmts";//vec_c
        String layerType = "";
        String matrixSet = "";
        String tiledFormat="";
        switch (serviceType) {
            case VEC_C:
                baseUrl = String.format(baseUrl,"vec_c");
                matrixSet="c";
                break;
            case CVA_C:
                baseUrl = String.format(baseUrl,"cva_c");
                matrixSet="c";
                break;
            case EVA_C:
                baseUrl = String.format(baseUrl,"eva_c");
                matrixSet="c";
                break;
            case IMG_C:
                baseUrl = String.format(baseUrl,"img_c");
                matrixSet="c";
                break;
            case CIA_C:
                baseUrl = String.format(baseUrl,"cia_c");
                matrixSet="c";
                break;
            case TER_C:
                baseUrl = String.format(baseUrl,"ter_c");
                matrixSet="c";
                break;
            case CTA_C:
                baseUrl = String.format(baseUrl,"cta_c");
                matrixSet="c";
                break;
            case VEC_W:
                baseUrl = String.format(baseUrl,"vec_w");
                matrixSet="w";
                break;
            case CVA_W:
                baseUrl = String.format(baseUrl,"cva_w");
                matrixSet="w";
                break;
            case EVA_W:
                baseUrl = String.format(baseUrl,"eva_w");
                matrixSet="w";
                break;
            case IMG_W:
                baseUrl = String.format(baseUrl,"img_w");
                matrixSet="w";
                break;
            case CIA_W:
                baseUrl = String.format(baseUrl,"cia_w");
                matrixSet="w";
                break;
            case TER_W:
                baseUrl = String.format(baseUrl,"ter_w");
                matrixSet="w";
                break;
            case CTA_W:
                baseUrl = String.format(baseUrl,"cia_w");
                matrixSet="w";
                break;
        }
        switch (layerName){
            case VECTOR:
                layerType = "vec";
                break;
            case VECTOR_ANNOTATION_CHINESE:
                layerType = "cva";
                break;
            case VECTOR_ANNOTATION_ENGLISH:
                layerType = "eva";
                break;
            case IMAGE:
                layerType = "img";
                break;
            case IMAGE_ANNOTATION_CHINESE:
                layerType = "cia";
                break;
            case IMAGE_ANNOTATION_ENGLISH:
                layerType = "eia";
                break;
            case TERRAIN:
                layerType = "ter";
                break;
            case TERRAIN_ANNOTATION_CHINESE:
                layerType = "cta";
                break;
        }
        switch (format){
            case PNG:
                tiledFormat="png";
                break;
            case TILES:
                tiledFormat="tiles";
                break;
            case JPEG:
                tiledFormat="jpeg";
                break;
        }
        templateUri=String.format(templateUri,layerType,matrixSet,tiledFormat);
        return baseUrl + templateUri;
    }
}
