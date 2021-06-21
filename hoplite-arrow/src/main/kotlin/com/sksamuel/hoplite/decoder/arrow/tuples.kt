package com.sksamuel.hoplite.decoder.arrow

import arrow.core.Tuple4
import arrow.core.Tuple5
import com.sksamuel.hoplite.fp.invalid
import com.sksamuel.hoplite.ConfigFailure
import com.sksamuel.hoplite.ConfigResult
import com.sksamuel.hoplite.ArrayNode
import com.sksamuel.hoplite.DecoderContext
import com.sksamuel.hoplite.Node
import com.sksamuel.hoplite.decoder.NullHandlingDecoder
import com.sksamuel.hoplite.fp.Validated
import com.sksamuel.hoplite.fp.flatMap
import kotlin.reflect.KType

class Tuple2Decoder : NullHandlingDecoder<Pair<*, *>> {

  override fun supports(type: KType): Boolean = type.classifier == Pair::class

  override fun safeDecode(node: Node,
                          type: KType,
                          context: DecoderContext): ConfigResult<Pair<*, *>> {

    fun decode(node: ArrayNode): ConfigResult<Pair<Any?, Any?>> {
      return if (node.elements.size == 2) {
        val aType = type.arguments[0].type!!
        val bType = type.arguments[1].type!!
        val adecoder = context.decoder(aType).flatMap { it.decode(node.atIndex(0), aType, context) }
        val bdecoder = context.decoder(bType).flatMap { it.decode(node.atIndex(1), bType, context) }
        Validated
          .ap(adecoder, bdecoder) { a, b -> Pair(a, b) }
          .mapInvalid { ConfigFailure.TupleErrors(node, it) }
      } else ConfigFailure.Generic("Tuple2 requires a list of two elements but list had size ${node.elements.size}").invalid()
    }

    return when (node) {
      is ArrayNode -> decode(node)
      else -> ConfigFailure.DecodeError(node, type).invalid()
    }
  }
}

class Tuple3Decoder : NullHandlingDecoder<Triple<*, *, *>> {

  override fun supports(type: KType): Boolean = type.classifier == Triple::class

  override fun safeDecode(node: Node,
                          type: KType,
                          context: DecoderContext): ConfigResult<Triple<*, *, *>> {

    fun decode(node: ArrayNode): ConfigResult<Triple<Any?, Any?, Any?>> {
      return if (node.elements.size == 3) {
        val aType = type.arguments[0].type!!
        val bType = type.arguments[1].type!!
        val cType = type.arguments[2].type!!
        val adecoder = context.decoder(aType).flatMap { it.decode(node.atIndex(0), aType, context) }
        val bdecoder = context.decoder(bType).flatMap { it.decode(node.atIndex(1), bType, context) }
        val cdecoder = context.decoder(cType).flatMap { it.decode(node.atIndex(2), cType, context) }
        Validated
          .ap(adecoder, bdecoder, cdecoder) { a, b, c -> Triple(a, b, c) }
          .mapInvalid { ConfigFailure.TupleErrors(node, it) }
      } else ConfigFailure.Generic("Tuple3 requires a list of three elements but list had size ${node.elements.size}").invalid()
    }

    return when (node) {
      is ArrayNode -> decode(node)
      else -> ConfigFailure.DecodeError(node, type).invalid()
    }
  }
}

class Tuple4Decoder : NullHandlingDecoder<Tuple4<*, *, *, *>> {

  override fun supports(type: KType): Boolean = type.classifier == Tuple4::class

  override fun safeDecode(node: Node,
                          type: KType,
                          context: DecoderContext): ConfigResult<Tuple4<*, *, *, *>> {

    fun decode(node: ArrayNode): ConfigResult<Tuple4<Any?, Any?, Any?, Any?>> {
      return if (node.elements.size == 4) {
        val aType = type.arguments[0].type!!
        val bType = type.arguments[1].type!!
        val cType = type.arguments[2].type!!
        val dType = type.arguments[3].type!!
        val adecoder = context.decoder(aType).flatMap { it.decode(node.atIndex(0), aType, context) }
        val bdecoder = context.decoder(bType).flatMap { it.decode(node.atIndex(1), bType, context) }
        val cdecoder = context.decoder(cType).flatMap { it.decode(node.atIndex(2), cType, context) }
        val ddecoder = context.decoder(dType).flatMap { it.decode(node.atIndex(3), dType, context) }
        Validated
          .ap(adecoder, bdecoder, cdecoder, ddecoder) { a, b, c, d -> Tuple4(a, b, c, d) }
          .mapInvalid { ConfigFailure.TupleErrors(node, it) }
      } else ConfigFailure.Generic("Tuple4 requires a list of four elements but list had size ${node.elements.size}").invalid()
    }

    return when (node) {
      is ArrayNode -> decode(node)
      else -> ConfigFailure.DecodeError(node, type).invalid()
    }
  }
}

class Tuple5Decoder : NullHandlingDecoder<Tuple5<*, *, *, *, *>> {

  override fun supports(type: KType): Boolean = type.classifier == Tuple5::class

  override fun safeDecode(node: Node,
                          type: KType,
                          context: DecoderContext): ConfigResult<Tuple5<*, *, *, *, *>> {

    fun decode(node: ArrayNode): ConfigResult<Tuple5<Any?, Any?, Any?, Any?, Any?>> {
      return if (node.elements.size == 5) {
        val aType = type.arguments[0].type!!
        val bType = type.arguments[1].type!!
        val cType = type.arguments[2].type!!
        val dType = type.arguments[3].type!!
        val eType = type.arguments[4].type!!
        val adecoder = context.decoder(aType).flatMap { it.decode(node.atIndex(0), aType, context) }
        val bdecoder = context.decoder(bType).flatMap { it.decode(node.atIndex(1), bType, context) }
        val cdecoder = context.decoder(cType).flatMap { it.decode(node.atIndex(2), cType, context) }
        val ddecoder = context.decoder(dType).flatMap { it.decode(node.atIndex(3), dType, context) }
        val edecoder = context.decoder(eType).flatMap { it.decode(node.atIndex(4), eType, context) }
        Validated.ap(
          adecoder,
          bdecoder,
          cdecoder,
          ddecoder,
          edecoder
        ) { a, b, c, d, e -> Tuple5(a, b, c, d, e) }
          .mapInvalid { ConfigFailure.TupleErrors(node, it) }
      } else ConfigFailure.Generic("Tuple5 requires a list of five elements but list had size ${node.elements.size}").invalid()
    }

    return when (node) {
      is ArrayNode -> decode(node)
      else -> ConfigFailure.DecodeError(node, type).invalid()
    }
  }
}
