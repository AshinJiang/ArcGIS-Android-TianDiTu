package com.shaunsheep.ags1001;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.esri.arcgisruntime.arcgisservices.TileInfo;
import com.esri.arcgisruntime.geometry.Envelope;
import com.esri.arcgisruntime.layers.WebTiledLayer;
import com.esri.arcgisruntime.layers.WmtsLayer;
import com.esri.arcgisruntime.loadable.LoadStatus;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.shaunsheep.ags1001.tdt.LayerInfoFactory;
import com.shaunsheep.ags1001.tdt.TianDiTuLayerTypes;
import com.shaunsheep.ags1001.tdtwebtiledlayer.TdtTemplateUri;
import com.shaunsheep.ags1001.tianditu.TianDiTuLayer;
import com.shaunsheep.ags1001.tianditu.TianDiTuLayerInfo;

import java.util.Arrays;

public class TianDiTuActivity extends AppCompatActivity {
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tian_di_tu);
        mapView=(MapView)findViewById(R.id.mapview);

        addWebTileds();
    }

    /**
     * 用WebTiledLayer加载天地图
     */
    private void addWebTileds(){
        String tempurl="http://t0.tianditu.com/vec_c/wmts?"+
                "service=wmts&request=gettile&version=1.0.0&layer=vec&STYLE=default"+
                "&tilematrixset=c&tilematrix={level}&tilerow={row}&tilecol={col}&format=tiles";

        String templateUri= TdtTemplateUri.getTemplateUri(TdtTemplateUri.ServiceType.CVA_C,
                TdtTemplateUri.LayerName.VECTOR_ANNOTATION_CHINESE,
                TdtTemplateUri.TiledFormat.PNG);

        final WebTiledLayer webTiledLayer = new WebTiledLayer(templateUri);

        webTiledLayer.addDoneLoadingListener(new Runnable() {
            public void run() {
                if (webTiledLayer.getLoadStatus() == LoadStatus.LOADED) {
                    // work with the layer here
                }
            }
        });
        String templateUri1= TdtTemplateUri.getTemplateUri(TdtTemplateUri.ServiceType.VEC_C,
                TdtTemplateUri.LayerName.VECTOR,
                TdtTemplateUri.TiledFormat.PNG);
        WebTiledLayer webTiledLayer1 = new WebTiledLayer(templateUri1);

        ArcGISMap map=new ArcGISMap();
        map.getOperationalLayers().add(webTiledLayer1);
        map.getOperationalLayers().add(webTiledLayer);
        mapView.setMap(map);
    }

    /**
     * 扩展ImageTiledLayer加载天地图
     */
    private void addTDT(){
        com.shaunsheep.ags1001.tdt.TianDiTuLayerInfo layerInfo=
                LayerInfoFactory.getLayerInfo(TianDiTuLayerTypes.TIANDITU_VECTOR_2000);
        TileInfo info=layerInfo.getTileInfo();
        Envelope fullExtent=layerInfo.getFullExtent();
        com.shaunsheep.ags1001.tdt.TianDiTuLayer layer=
                new com.shaunsheep.ags1001.tdt.TianDiTuLayer(info,fullExtent);
        layer.setLayerInfo(layerInfo);

        ArcGISMap map =new ArcGISMap();
        map.getBasemap().getBaseLayers().add(layer);
        mapView.setMap(map);
    }
    /**
     * 扩展ImageTiledLayer加载天地图
     */
    private void addTdt(){
        TianDiTuLayerInfo tdtInfo = new TianDiTuLayerInfo();
        TianDiTuLayerInfo tdtInfo01 = tdtInfo.initwithlayerType(TianDiTuLayerInfo.TianDiTuLayerType.TDT_IMAGE,
                TianDiTuLayerInfo.TianDiTuSpatialReferenceType.TDT_2000);
        TianDiTuLayer ltl1 = new TianDiTuLayer(tdtInfo01.getTileInfo(), tdtInfo01.getFullExtent());
        ltl1.setLayerInfo(tdtInfo01);

        TianDiTuLayerInfo tdtannoInfo = new TianDiTuLayerInfo();
        TianDiTuLayerInfo tdtannoInfo02 = tdtannoInfo.initwithlayerType(TianDiTuLayerInfo.TianDiTuLayerType.TDT_IMAGE,
                TianDiTuLayerInfo.TianDiTuLanguageType.TDT_CN,
                TianDiTuLayerInfo.TianDiTuSpatialReferenceType.TDT_2000);
        TianDiTuLayer ltl2 = new TianDiTuLayer(tdtannoInfo02.getTileInfo(), tdtannoInfo02.getFullExtent());
        ltl2.setLayerInfo(tdtannoInfo02);

        ArcGISMap map =new ArcGISMap();
        map.getBasemap().getBaseLayers().add(ltl1);
        map.getBasemap().getBaseLayers().add(ltl2);
        mapView.setMap(map);
    }
}
