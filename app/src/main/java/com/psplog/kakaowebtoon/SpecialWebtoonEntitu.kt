package com.psplog.kakaowebtoon

data class SpecialWebtoonEntity(
    val id: Int,
    val title: String,
    val thumbnail: String,
    val tags: List<String>
) {
    companion object {
        val dummyList = mutableListOf<SpecialWebtoonEntity>(
            SpecialWebtoonEntity(
                0,
                "꾸울잼 웹툰1",
                "https://blog.kakaocdn.net/dn/bOwW4G/btq45OO6gXo/LJr1vbbXgV2cgkV6DK0j80/img.gif",
                listOf(
                    "노잼",
                    "꿀잼",
                    "거짓말",
                    "컴포즈 싫어",
                    "노잼",
                    "꿀잼",
                    "거짓말",
                    "컴포즈 싫어",
                    "노잼",
                    "꿀잼",
                    "거짓말",
                    "컴포즈 싫어"
                )
            ),
            SpecialWebtoonEntity(
                1,
                "꾸울잼 웹툰2",
                "https://blog.kakaocdn.net/dn/bOwW4G/btq45OO6gXo/LJr1vbbXgV2cgkV6DK0j80/img.gif",
                listOf(
                    "노잼",
                    "꿀잼",
                    "거짓말",
                    "컴포즈 싫어",
                    "노잼",
                    "꿀잼",
                    "거짓말",
                    "컴포즈 싫어",
                    "노잼",
                    "꿀잼",
                    "거짓말",
                    "컴포즈 싫어",
                    "노잼",
                    "꿀잼",
                    "거짓말",
                    "컴포즈 싫어",
                    "노잼",
                    "꿀잼",
                    "거짓말",
                    "컴포즈 싫어",
                    "노잼",
                    "꿀잼",
                    "거짓말",
                    "컴포즈 싫어"
                )
            ), SpecialWebtoonEntity(
                2,
                "노오잼잼 웹툰1",
                "https://blog.kakaocdn.net/dn/bOwW4G/btq45OO6gXo/LJr1vbbXgV2cgkV6DK0j80/img.gif",
                listOf(
                    "노잼",
                    "꿀잼",
                    "거짓말",
                    "컴포즈 싫어",
                    "노잼",
                    "꿀잼",
                    "거짓말",
                    "컴포즈 싫어",
                    "노잼",
                    "꿀잼",
                    "거짓말",
                    "컴포즈 싫어",
                    "노잼",
                    "꿀잼",
                    "거짓말",
                    "컴포즈 싫어",
                    "노잼",
                    "꿀잼",
                    "거짓말",
                    "컴포즈 싫어",
                    "노잼",
                    "꿀잼",
                    "거짓말",
                    "컴포즈 싫어"
                )
            ), SpecialWebtoonEntity(
                3,
                "꾸울잼잼 웹툰3",
                "https://blog.kakaocdn.net/dn/bOwW4G/btq45OO6gXo/LJr1vbbXgV2cgkV6DK0j80/img.gif",
                listOf(
                    "노잼",
                    "꿀잼",
                    "거짓말",
                    "컴포즈 싫어",
                    "노잼",
                    "꿀잼",
                    "거짓말",
                    "컴포즈 싫어",
                    "노잼",
                    "꿀잼",
                    "거짓말",
                    "컴포즈 싫어",
                    "노잼",
                    "꿀잼",
                    "거짓말",
                    "컴포즈 싫어",
                    "노잼",
                    "꿀잼",
                    "거짓말",
                    "컴포즈 싫어",
                    "노잼",
                    "꿀잼",
                    "거짓말",
                    "컴포즈 싫어"
                )
            )
        )
    }
}