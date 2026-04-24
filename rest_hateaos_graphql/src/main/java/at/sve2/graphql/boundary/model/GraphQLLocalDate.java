package at.sve2.graphql.boundary.model;

import graphql.language.Node;
import graphql.language.ScalarTypeDefinition;
import graphql.language.ScalarTypeExtensionDefinition;
import graphql.schema.*;
import graphql.util.TraversalControl;
import graphql.util.TraverserContext;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.List;


public class GraphQLLocalDate implements GraphQLNamedInputType, GraphQLNamedOutputType, GraphQLUnmodifiedType, GraphQLNullableType {


    @Override
    public @NonNull String getName() {
        return "";
    }

    @Override
    public @Nullable String getDescription() {
        return "";
    }

    @Override
    public @Nullable Node getDefinition() {
        return null;
    }

    @Override
    public TraversalControl accept(TraverserContext<GraphQLSchemaElement> context, GraphQLTypeVisitor visitor) {
        return null;
    }

    @Override
    public GraphQLSchemaElement copy() {
        return null;
    }
}
