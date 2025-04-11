package io.cdap.wrangler.directive;

import io.cdap.wrangler.api.Row;
import io.cdap.wrangler.api.parser.ByteSize;
import io.cdap.wrangler.api.parser.TimeDuration;
import io.cdap.wrangler.api.Directive;
import io.cdap.wrangler.api.DirectiveContext;
import io.cdap.wrangler.api.DirectiveInfo;
import io.cdap.wrangler.api.DirectiveName;
import io.cdap.wrangler.api.Arguments;
import io.cdap.wrangler.api.Initializer;

import java.util.ArrayList;
import java.util.List;

@Directive(
    name = "aggregate-stats",
    description = "Aggregates numeric values (byte sizes and time durations) and appends totals to each row"
)
public class AggregateStats implements Directive, Initializer {
    private String sizeColumn;
    private String timeColumn;
    private String outputSizeColumn;
    private String outputTimeColumn;

    @Override
    public void initialize(DirectiveContext context, Arguments args) {
        sizeColumn = args.value("size_column");
        timeColumn = args.value("time_column");
        outputSizeColumn = args.value("output_size_column");
        outputTimeColumn = args.value("output_time_column");
    }

    @Override
    public List<Row> execute(List<Row> rows) throws Exception {
        double totalBytes = 0;
        double totalMilliseconds = 0;

        for (Row row : rows) {
            String sizeStr = row.getValue(sizeColumn).toString();
            String timeStr = row.getValue(timeColumn).toString();

            totalBytes += ByteSize.toBytes(sizeStr);
            totalMilliseconds += TimeDuration.toMilliseconds(timeStr);
        }

        List<Row> result = new ArrayList<>();
        for (Row row : rows) {
            row.add(outputSizeColumn, totalBytes / (1024 * 1024)); // MB
            row.add(outputTimeColumn, totalMilliseconds / 1000); // seconds
            result.add(row);
        }

        return result;
    }
}
