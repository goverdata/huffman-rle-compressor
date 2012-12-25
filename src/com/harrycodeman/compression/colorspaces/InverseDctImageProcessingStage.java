package com.harrycodeman.compression.colorspaces;

import static com.harrycodeman.compression.colorspaces.DctMatrices.*;

public class InverseDctImageProcessingStage implements IImageProcessingStage {
    @Override
    public Image executeFor(Image image) throws Exception {
        for (ImagePart p : image.get8x8Parts()) {
            CoefficientsMatrixBase firstComponentMatrix = new CoefficientsMatrixWrapperForImagePart(p, 0);
            firstComponentMatrix.copyByPositiveElementFrom(
                    T_DCT_TABLE.multiply(
                            firstComponentMatrix.multiplyByElement(LUMINANCE_QT)
                    ).multiply(DCT_TABLE)
            );

            CoefficientsMatrixBase secondComponentMatrix = new CoefficientsMatrixWrapperForImagePart(p, 1);
            secondComponentMatrix.copyByPositiveElementFrom(
                    T_DCT_TABLE.multiply(
                            secondComponentMatrix.multiplyByElement(CHROMINANCE_QR)
                    ).multiply(DCT_TABLE)

            );

            CoefficientsMatrixBase thirdComponentMatrix = new CoefficientsMatrixWrapperForImagePart(p, 2);
            thirdComponentMatrix.copyByPositiveElementFrom(
                    T_DCT_TABLE.multiply(
                            thirdComponentMatrix.multiplyByElement(CHROMINANCE_QR)
                    ).multiply(DCT_TABLE)
            );
        }
        return image;
    }
}
