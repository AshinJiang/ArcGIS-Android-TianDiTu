package com.shaunsheep.ags1001.tdt;

import com.esri.arcgisruntime.arcgisservices.TileInfo;
import com.esri.arcgisruntime.data.TileKey;
import com.esri.arcgisruntime.geometry.Envelope;
import com.esri.arcgisruntime.layers.ImageTiledLayer;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jiang on 2017/4/17.
 */

public final class TianDiTuLayer extends ImageTiledLayer {
    private TianDiTuLayerInfo layerInfo;

    public TianDiTuLayer(TileInfo tileInfo, Envelope fullExtent) {
        super(tileInfo, fullExtent);
    }

    public void setLayerInfo(TianDiTuLayerInfo layerInfo) {
        this.layerInfo = layerInfo;
    }

    public TianDiTuLayerInfo getLayerInfo() {
        return this.layerInfo;
    }

    @Override
    protected byte[] getTile(TileKey tileKey) {
        int level = tileKey.getLevel();
        int col = tileKey.getColumn();
        int row = tileKey.getRow();
        try {
            return getTile(level, col, row);
        } catch (Exception e) {
            return new byte[0];
        }
    }

    private byte[] getTile(int level, int col, int row) throws Exception {
        byte[] tileImage = null;
        if (layerInfo != null) {
            if (tileImage == null) {
                if (level > layerInfo.getMaxZoomLevel()
                        || level < layerInfo.getMinZoomLevel())
                    return new byte[0];
                String path = layerInfo.getUrl()
                        + "?service=wmts&request=gettile&version=1.0.0&layer="
                        + layerInfo.getLayerName() + "&format=tiles&tilematrixset="
                        + layerInfo.getTileMatrixSet() + "&tilecol=" + col
                        + "&tilerow=" + row + "&tilematrix=" + (level);

                URL url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(5000);
                //获取服务器返回回来的流
                InputStream is = conn.getInputStream();
                tileImage = getBytes(is);
            }
        }
        return tileImage;
    }

    private byte[] getBytes(InputStream is) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = is.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        is.close();
        bos.flush();
        byte[] result = bos.toByteArray();
        System.out.println(new String(result));
        return result;
    }
}
