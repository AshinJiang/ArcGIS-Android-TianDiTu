package com.shaunsheep.ags1001.tianditu;

import com.esri.arcgisruntime.arcgisservices.TileInfo;
import com.esri.arcgisruntime.data.TileKey;
import com.esri.arcgisruntime.geometry.Envelope;
import com.esri.arcgisruntime.layers.ImageTiledLayer;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jiang on 2017/6/30.
 */

public class TianDiTuLayer extends ImageTiledLayer {
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
        String mainURL = layerInfo.getTianDiTuServiceURL();
        String requestUrl = mainURL + "&tilecol=" + col + "&tilerow=" + row + "&tilematrix=" + (level);
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            //获取服务器返回回来的流
            InputStream is = conn.getInputStream();
            return getBytes(is);
        } catch (Exception e) {
            return new byte[0];
        }
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
