package com.vladsch.flexmark.ast.util;

import com.vladsch.flexmark.ast.*;

public class BlockVisitorExt {
    static <V extends BlockVisitor> VisitHandler<?>[] VISIT_HANDLERS(final V visitor) {
        return new VisitHandler<?>[] {
                new VisitHandler<>(BlockQuote.class, new Visitor<BlockQuote>() {
                    @Override
                    public void visit(BlockQuote node) {
                        visitor.visit(node);
                    }
                }),
                new VisitHandler<>(BulletList.class, new Visitor<BulletList>() {
                    @Override
                    public void visit(BulletList node) {
                        visitor.visit(node);
                    }
                }),
                new VisitHandler<>(Document.class, new Visitor<Document>() {
                    @Override
                    public void visit(Document node) {
                        visitor.visit(node);
                    }
                }),
                new VisitHandler<>(FencedCodeBlock.class, new Visitor<FencedCodeBlock>() {
                    @Override
                    public void visit(FencedCodeBlock node) {
                        visitor.visit(node);
                    }
                }),
                new VisitHandler<>(Heading.class, new Visitor<Heading>() {
                    @Override
                    public void visit(Heading node) {
                        visitor.visit(node);
                    }
                }),
                new VisitHandler<>(HtmlBlock.class, new Visitor<HtmlBlock>() {
                    @Override
                    public void visit(HtmlBlock node) {
                        visitor.visit(node);
                    }
                }),
                new VisitHandler<>(HtmlCommentBlock.class, new Visitor<HtmlCommentBlock>() {
                    @Override
                    public void visit(HtmlCommentBlock node) {
                        visitor.visit(node);
                    }
                }),
                new VisitHandler<>(IndentedCodeBlock.class, new Visitor<IndentedCodeBlock>() {
                    @Override
                    public void visit(IndentedCodeBlock node) {
                        visitor.visit(node);
                    }
                }),
                new VisitHandler<>(BulletListItem.class, new Visitor<BulletListItem>() {
                    @Override
                    public void visit(BulletListItem node) {
                        visitor.visit(node);
                    }
                }),
                new VisitHandler<>(OrderedListItem.class, new Visitor<OrderedListItem>() {
                    @Override
                    public void visit(OrderedListItem node) {
                        visitor.visit(node);
                    }
                }),
                new VisitHandler<>(OrderedList.class, new Visitor<OrderedList>() {
                    @Override
                    public void visit(OrderedList node) {
                        visitor.visit(node);
                    }
                }),
                new VisitHandler<>(Paragraph.class, new Visitor<Paragraph>() {
                    @Override
                    public void visit(Paragraph node) {
                        visitor.visit(node);
                    }
                }),
                new VisitHandler<>(Reference.class, new Visitor<Reference>() {
                    @Override
                    public void visit(Reference node) {
                        visitor.visit(node);
                    }
                }),
                new VisitHandler<>(ThematicBreak.class, new Visitor<ThematicBreak>() {
                    @Override
                    public void visit(ThematicBreak node) {
                        visitor.visit(node);
                    }
                })
        };
    }
}