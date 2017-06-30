package com.shaunsheep.ags1001;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.esri.arcgisruntime.arcgisservices.TileInfo;
import com.esri.arcgisruntime.geometry.Envelope;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.shaunsheep.ags1001.tdt.LayerInfoFactory;
import com.shaunsheep.ags1001.tdt.TianDiTuLayerTypes;
import com.shaunsheep.ags1001.tianditu.TianDiTuLayer;
import com.shaunsheep.ags1001.tianditu.TianDiTuLayerInfo;

public class TianDiTuActivity extends AppCompatActivity {
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tian_di_tu);
        mapView=(MapView)findViewById(R.id.mapview);

        addTdt();
    }
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
