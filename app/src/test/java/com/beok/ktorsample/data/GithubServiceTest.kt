package com.beok.ktorsample.data

import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel
import java.io.File
import kotlin.test.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GithubServiceTest {

    @Test
    fun `유저의 레파지토리 리스트를 조회합니다`() = runBlocking {
        // given
        val user = "beokbeok"
        val mockEngine = MockEngine {
            respond(
                content = ByteReadChannel(File(PATH_SEARCH_JSON).readText()),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
        val service = GithubService(engine = mockEngine)

        // when
        val actual = service.fetchRepositoryList(user = user)

        // then
        assertEquals(expected = 30, actual = actual.size)
        assertEquals(expected = 260609949, actual = actual.first().id)
    }

    companion object {
        private const val PATH_SEARCH_JSON = "src/test/resources/search_repo.json"
    }
}
