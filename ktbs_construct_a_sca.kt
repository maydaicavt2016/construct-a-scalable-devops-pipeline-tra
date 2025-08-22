data class DevOpsPipeline(
    val id: Int,
    val name: String,
    val stages: List<PipelineStage>
)

data class PipelineStage(
    val id: Int,
    val name: String,
    val status: PipelineStatus,
    val jobs: List<PipelineJob>
)

data class PipelineJob(
    val id: Int,
    val name: String,
    val status: PipelineStatus,
    val startTime: String,
    val endTime: String
)

enum class PipelineStatus {
    NOT_STARTED,
    IN_PROGRESS,
    SUCCESS,
    FAILED
}

data class PipelineTracker(
    val pipelines: MutableList<DevOpsPipeline> = mutableListOf()
) {
    fun addPipeline(pipeline: DevOpsPipeline) {
        pipelines.add(pipeline)
    }

    fun getPipeline(pipelineId: Int): DevOpsPipeline? {
        return pipelines.find { it.id == pipelineId }
    }

    fun updatePipelineStage(pipelineId: Int, stageId: Int, status: PipelineStatus) {
        pipelines.find { it.id == pipelineId }?.stages?.find { it.id == stageId }?.status = status
    }

    fun updatePipelineJob(pipelineId: Int, stageId: Int, jobId: Int, status: PipelineStatus) {
        pipelines.find { it.id == pipelineId }?.stages?.find { it.id == stageId }?.jobs?.find { it.id == jobId }?.status = status
    }
}