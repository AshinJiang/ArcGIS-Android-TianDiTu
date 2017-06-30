package com.shaunsheep.ags1001.tianditu;

import com.esri.arcgisruntime.arcgisservices.LevelOfDetail;
import com.esri.arcgisruntime.arcgisservices.TileInfo;
import com.esri.arcgisruntime.geometry.Envelope;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiang on 2017/6/30.
 */

public class TianDiTuLayerInfo {
    public enum TianDiTuSpatialReferenceType {
        TDT_MERCATOR,  //天地图墨卡托服务
        TDT_2000 //<天地图2000服务>
    }

    public enum TianDiTuLanguageType {
        TDT_CN, //<天地图中文标注服务>
        TDT_EN //<天地图英文标注服务>
    }

    public enum TianDiTuLayerType {
        TDT_VECTOR, ///<天地图矢量服务>
        TDT_IMAGE, ///<天地图影像务>
        TDT_TERRAIN ///<天地图地形服务>
    }

    private String layername;
    private String servicename;
    private String tilematrixset;
    private SpatialReference sp;
    private Envelope fullExtent;
    private Point origin;
    private List<LevelOfDetail> lods;
    private TileInfo tileInfo;

    public String getLayername() {
        return layername;
    }

    public String getServicename() {
        return servicename;
    }

    public String getTilematrixset() {
        return tilematrixset;
    }

    public SpatialReference getSp() {
        return sp;
    }

    public Point getOrigin() {
        return origin;
    }

    public List<LevelOfDetail> getLods() {
        return lods;
    }

    public Envelope getFullExtent() {
        return fullExtent;
    }

    public TileInfo getTileInfo() {
        return tileInfo;
    }

    String kURLGetTile = "http://t0.tianditu.com/%s/wmts?service=wmts&request=gettile&version=1.0.0&layer=%s&format=tiles&tilematrixset=%s";

    double X_MIN_MERCATOR = -20037508.3427892;
    double Y_MIN_MERCATOR = -20037508.3427892;
    double X_MAX_MERCATOR = 20037508.3427892;
    double Y_MAX_MERCATOR = 20037508.3427892;

    double X_MIN_2000 = -180.0;
    double Y_MIN_2000 = -90.0;
    double X_MAX_2000 = 180.0;
    double Y_MAX_2000 = 90.0;

    int _minZoomLevel = 0;
    int _maxZoomLevel = 16;
    static int _tileWidth = 256;
    static int _tileHeight = 256;
    static int _dpi = 96;

    static int _WebMercator = 102100;
    int _GCS2000 = 2000;

    String kTILE_MATRIX_SET_MERCATOR = "w";
    String kTILE_MATRIX_SET_2000 = "c";


    public TianDiTuLayerInfo initwithlayerType(TianDiTuLayerType layerType, TianDiTuSpatialReferenceType sptype) {
        layername = "";
        switch (layerType) {
            case TDT_VECTOR:
                layername = "vec";
                break;
            case TDT_IMAGE:
                layername = "img";
                break;
            case TDT_TERRAIN:
                layername = "ter";
                break;
            default:
                break;
        }
        setSpatialReference(sptype);
        tileInfo = getTianDiTuLayerInfo();
        return this;
    }

    public TianDiTuLayerInfo initwithlayerType(TianDiTuLayerType layerType, TianDiTuLanguageType lan, TianDiTuSpatialReferenceType sptype) {
        layername = "";
        switch (layerType) {
            case TDT_IMAGE:
                switch (lan) {
                    case TDT_CN:
                        layername = "cva";
                        break;
                    case TDT_EN:
                        layername = "eva";
                        break;
                    default:
                        break;
                }
                break;
            case TDT_TERRAIN:
                switch (lan) {
                    case TDT_CN:
                        layername = "cia";
                        break;
                    case TDT_EN:
                        layername = "eia";
                        break;
                    default:
                        break;
                }
                break;
            case TDT_VECTOR:
                layername = "ter";
                break;
            default:
                break;
        }
        setSpatialReference(sptype);
        tileInfo = getTianDiTuLayerInfo();
        return this;
    }

    public void setSpatialReference(TianDiTuSpatialReferenceType sptype) {
        sp = SpatialReference.create(_WebMercator);
        switch (sptype) {
            case TDT_MERCATOR:
                sp = SpatialReference.create(_WebMercator);
                servicename = layername + "_" + kTILE_MATRIX_SET_MERCATOR;
                this.tilematrixset = kTILE_MATRIX_SET_MERCATOR;
                this.origin = new Point(X_MIN_MERCATOR, Y_MAX_MERCATOR, sp);
                this.fullExtent = new Envelope(X_MIN_MERCATOR, Y_MIN_MERCATOR, X_MAX_MERCATOR, Y_MAX_MERCATOR, sp);
                this.lods = new ArrayList<LevelOfDetail>();

                lods.add(new LevelOfDetail(1, 78271.51696402048, 2.958293554545656E8));
                lods.add(new LevelOfDetail(2, 39135.75848201024, 1.479146777272828E8));
                lods.add(new LevelOfDetail(3, 19567.87924100512, 7.39573388636414E7));
                lods.add(new LevelOfDetail(4, 9783.93962050256, 3.69786694318207E7));
                lods.add(new LevelOfDetail(5, 4891.96981025128, 1.848933471591035E7));
                lods.add(new LevelOfDetail(6, 72445.98490512564, 9244667.35795517));
                lods.add(new LevelOfDetail(7, 1222.99245256282, 4622333.678977588));
                lods.add(new LevelOfDetail(8, 611.49622628141, 2311166.839488794));
                lods.add(new LevelOfDetail(9, 305.748113140705, 1155583.419744397));
                lods.add(new LevelOfDetail(10, 152.8740565703525, 577791.7098721985));
                lods.add(new LevelOfDetail(11, 76.43702828517625, 288895.85493609926));
                lods.add(new LevelOfDetail(12, 38.21851414258813, 144447.92746804963));
                lods.add(new LevelOfDetail(13, 19.109257071294063, 72223.96373402482));
                lods.add(new LevelOfDetail(14, 9.554628535647032, 36111.98186701241));
                lods.add(new LevelOfDetail(15, 4.777314267823516, 18055.990933506204));
                lods.add(new LevelOfDetail(16, 2.388657133911758, 9027.995466753102));
                lods.add(new LevelOfDetail(17, 1.194328566955879, 4513.997733376551));
                lods.add(new LevelOfDetail(18, 0.5971642834779395, 2256.998866688275));

                break;
            case TDT_2000:
                sp = SpatialReference.create(_GCS2000);
                servicename = layername + "_" + kTILE_MATRIX_SET_2000;
                this.tilematrixset = kTILE_MATRIX_SET_2000;
                this.origin = new Point(X_MIN_2000, Y_MAX_2000, sp);
                this.fullExtent = new Envelope(X_MIN_2000, Y_MIN_2000, X_MAX_2000, Y_MAX_2000, sp);
                this.lods = new ArrayList<LevelOfDetail>();
                lods.add(new LevelOfDetail(1, 0.7031249999891485, 2.958293554545656E8));
                lods.add(new LevelOfDetail(2, 0.35156249999999994, 1.479146777272828E8));
                lods.add(new LevelOfDetail(3, 0.17578124999999997, 7.39573388636414E7));
                lods.add(new LevelOfDetail(4, 0.08789062500000014, 3.69786694318207E7));
                lods.add(new LevelOfDetail(5, 0.04394531250000007, 1.848933471591035E7));
                lods.add(new LevelOfDetail(6, 0.021972656250000007, 9244667.357955175));
                lods.add(new LevelOfDetail(7, 0.01098632812500002, 4622333.678977588));
                lods.add(new LevelOfDetail(8, 0.00549316406250001, 2311166.839488794));
                lods.add(new LevelOfDetail(9, 0.0027465820312500017, 1155583.419744397));
                lods.add(new LevelOfDetail(10, 0.0013732910156250009, 577791.7098721985));
                lods.add(new LevelOfDetail(11, 0.000686645507812499, 288895.85493609926));
                lods.add(new LevelOfDetail(12, 0.0003433227539062495, 144447.92746804963));
                lods.add(new LevelOfDetail(13, 0.00017166137695312503, 72223.96373402482));
                lods.add(new LevelOfDetail(14, 0.00008583068847656251, 36111.98186701241));
                lods.add(new LevelOfDetail(15, 0.000042915344238281406, 18055.990933506204));
                lods.add(new LevelOfDetail(16, 0.000021457672119140645, 9027.995466753102));
                lods.add(new LevelOfDetail(17, 0.000010728836059570307, 4513.997733376551));
                lods.add(new LevelOfDetail(18, 0.000005364418029785169, 2256.998866688275));

                break;
            default:
                break;
        }
    }

    public TileInfo getTianDiTuLayerInfo() {
        TileInfo tileInfo001 = new TileInfo(_dpi, TileInfo.ImageFormat.PNG, lods, origin, sp, _tileHeight, _tileWidth);
        return tileInfo001;
    }

    public String getTianDiTuServiceURL() {
        String wmtsURL = String.format(kURLGetTile, servicename, layername, tilematrixset);
        return wmtsURL;
    }
}
