package com.onurkaraduman.loopify.ui.screens.detail

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.onurkaraduman.loopify.data.remote.dto.detail.ReviewDetail
import com.onurkaraduman.loopify.domain.model.details.ProductDetailsModel
import com.onurkaraduman.loopify.ui.screens.detail.DetailContract.DetailUiState
class DetailScreenPreviewProvider: PreviewParameterProvider<DetailUiState> {
    override val values: Sequence<DetailUiState>
        get() = sequenceOf(
            DetailUiState(
                isLoading = true,
                errorMessage = null,
                productDetails = null
            ),
            DetailUiState(
                isLoading = false,
                errorMessage = "Not Found",
                productDetails = null
            ),
            DetailUiState(
                isLoading = false,
                errorMessage = null,
                productDetails = ProductDetailsModel(
                    id = 1,
                    title = "Huawei Watch GT4 Pro",
                    descriptions = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. ",
                    price = 245.99,
                    rating = 2.0,
                    stock = 5,
                    imageList = listOf("image 1","image2","image3"),
                    discountPercentage = 7.5,
                    tagList = listOf("tags1","tags2"),
                    reviewList = listOf(
                        ReviewDetail(
                            comment = "Awesome",
                            date = "25/09/2024",
                            rating = 5,
                            reviewerEmail = "example@example.com",
                            reviewerName = "John Doe"
                        ),
                        ReviewDetail(
                            comment = "Not Bad",
                            date = "27/09/2024",
                            rating = 3,
                            reviewerEmail = "Rick@example.com",
                            reviewerName = "Rick"
                        )
                    )

                )
            )
        )
}