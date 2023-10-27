/**
 * Autogenerated by Avro
 * <p>
 * DO NOT EDIT DIRECTLY
 */
package com.demo.avro;

import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.SchemaStore;
import org.apache.avro.specific.SpecificData;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class UserSessionsValue extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
    public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"UserSessionsValue\",\"namespace\":\"com.demo.avro\",\"fields\":[{\"name\":\"anonymous_id\",\"type\":\"int\"},{\"name\":\"action\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"event_timestamp\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"event_datetime\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"session_id\",\"type\":[\"null\",\"string\"],\"default\":null}]}");
    private static final long serialVersionUID = 3479562235181044699L;
    private static SpecificData MODEL$ = new SpecificData();
    private static final BinaryMessageEncoder<UserSessionsValue> ENCODER =
            new BinaryMessageEncoder<UserSessionsValue>(MODEL$, SCHEMA$);
    private static final BinaryMessageDecoder<UserSessionsValue> DECODER =
            new BinaryMessageDecoder<UserSessionsValue>(MODEL$, SCHEMA$);
    @SuppressWarnings("unchecked")
    private static final org.apache.avro.io.DatumWriter<UserSessionsValue>
            WRITER$ = (org.apache.avro.io.DatumWriter<UserSessionsValue>) MODEL$.createDatumWriter(SCHEMA$);
    @SuppressWarnings("unchecked")
    private static final org.apache.avro.io.DatumReader<UserSessionsValue>
            READER$ = (org.apache.avro.io.DatumReader<UserSessionsValue>) MODEL$.createDatumReader(SCHEMA$);
    private int anonymous_id;
    private java.lang.CharSequence action;
    private java.lang.CharSequence event_timestamp;
    private java.lang.CharSequence event_datetime;
    private java.lang.CharSequence session_id;
    /**
     * Default constructor.  Note that this does not initialize fields
     * to their default values from the schema.  If that is desired then
     * one should use <code>newBuilder()</code>.
     */
    public UserSessionsValue() {
    }
    /**
     * All-args constructor.
     * @param anonymous_id The new value for anonymous_id
     * @param action The new value for action
     * @param event_timestamp The new value for event_timestamp
     * @param event_datetime The new value for event_datetime
     * @param session_id The new value for session_id
     */
    public UserSessionsValue(java.lang.Integer anonymous_id, java.lang.CharSequence action, java.lang.CharSequence event_timestamp, java.lang.CharSequence event_datetime, java.lang.CharSequence session_id) {
        this.anonymous_id = anonymous_id;
        this.action = action;
        this.event_timestamp = event_timestamp;
        this.event_datetime = event_datetime;
        this.session_id = session_id;
    }

    public static org.apache.avro.Schema getClassSchema() {
        return SCHEMA$;
    }

    /**
     * Return the BinaryMessageDecoder instance used by this class.
     */
    public static BinaryMessageDecoder<UserSessionsValue> getDecoder() {
        return DECODER;
    }

    /**
     * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
     * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
     */
    public static BinaryMessageDecoder<UserSessionsValue> createDecoder(SchemaStore resolver) {
        return new BinaryMessageDecoder<UserSessionsValue>(MODEL$, SCHEMA$, resolver);
    }

    /** Deserializes a UserSessionsValue from a ByteBuffer. */
    public static UserSessionsValue fromByteBuffer(
            java.nio.ByteBuffer b) throws java.io.IOException {
        return DECODER.decode(b);
    }

    /**
     * Creates a new UserSessionsValue RecordBuilder.
     * @return A new UserSessionsValue RecordBuilder
     */
    public static com.demo.avro.UserSessionsValue.Builder newBuilder() {
        return new com.demo.avro.UserSessionsValue.Builder();
    }

    /**
     * Creates a new UserSessionsValue RecordBuilder by copying an existing Builder.
     * @param other The existing builder to copy.
     * @return A new UserSessionsValue RecordBuilder
     */
    public static com.demo.avro.UserSessionsValue.Builder newBuilder(com.demo.avro.UserSessionsValue.Builder other) {
        return new com.demo.avro.UserSessionsValue.Builder(other);
    }

    /**
     * Creates a new UserSessionsValue RecordBuilder by copying an existing UserSessionsValue instance.
     * @param other The existing instance to copy.
     * @return A new UserSessionsValue RecordBuilder
     */
    public static com.demo.avro.UserSessionsValue.Builder newBuilder(com.demo.avro.UserSessionsValue other) {
        return new com.demo.avro.UserSessionsValue.Builder(other);
    }

    /** Serializes this UserSessionsValue to a ByteBuffer. */
    public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
        return ENCODER.encode(this);
    }

    public org.apache.avro.Schema getSchema() {
        return SCHEMA$;
    }

    // Used by DatumWriter.  Applications should not call.
    public java.lang.Object get(int field$) {
        switch (field$) {
            case 0:
                return anonymous_id;
            case 1:
                return action;
            case 2:
                return event_timestamp;
            case 3:
                return event_datetime;
            case 4:
                return session_id;
            default:
                throw new org.apache.avro.AvroRuntimeException("Bad index");
        }
    }

    // Used by DatumReader.  Applications should not call.
    @SuppressWarnings(value = "unchecked")
    public void put(int field$, java.lang.Object value$) {
        switch (field$) {
            case 0:
                anonymous_id = (java.lang.Integer) value$;
                break;
            case 1:
                action = (java.lang.CharSequence) value$;
                break;
            case 2:
                event_timestamp = (java.lang.CharSequence) value$;
                break;
            case 3:
                event_datetime = (java.lang.CharSequence) value$;
                break;
            case 4:
                session_id = (java.lang.CharSequence) value$;
                break;
            default:
                throw new org.apache.avro.AvroRuntimeException("Bad index");
        }
    }

    /**
     * Gets the value of the 'anonymous_id' field.
     * @return The value of the 'anonymous_id' field.
     */
    public java.lang.Integer getAnonymousId() {
        return anonymous_id;
    }

    /**
     * Sets the value of the 'anonymous_id' field.
     * @param value the value to set.
     */
    public void setAnonymousId(java.lang.Integer value) {
        this.anonymous_id = value;
    }

    /**
     * Gets the value of the 'action' field.
     * @return The value of the 'action' field.
     */
    public java.lang.CharSequence getAction() {
        return action;
    }

    /**
     * Sets the value of the 'action' field.
     * @param value the value to set.
     */
    public void setAction(java.lang.CharSequence value) {
        this.action = value;
    }

    /**
     * Gets the value of the 'event_timestamp' field.
     * @return The value of the 'event_timestamp' field.
     */
    public java.lang.CharSequence getEventTimestamp() {
        return event_timestamp;
    }

    /**
     * Sets the value of the 'event_timestamp' field.
     * @param value the value to set.
     */
    public void setEventTimestamp(java.lang.CharSequence value) {
        this.event_timestamp = value;
    }

    /**
     * Gets the value of the 'event_datetime' field.
     * @return The value of the 'event_datetime' field.
     */
    public java.lang.CharSequence getEventDatetime() {
        return event_datetime;
    }

    /**
     * Sets the value of the 'event_datetime' field.
     * @param value the value to set.
     */
    public void setEventDatetime(java.lang.CharSequence value) {
        this.event_datetime = value;
    }

    /**
     * Gets the value of the 'session_id' field.
     * @return The value of the 'session_id' field.
     */
    public java.lang.CharSequence getSessionId() {
        return session_id;
    }

    /**
     * Sets the value of the 'session_id' field.
     * @param value the value to set.
     */
    public void setSessionId(java.lang.CharSequence value) {
        this.session_id = value;
    }

    @Override
    public void writeExternal(java.io.ObjectOutput out)
            throws java.io.IOException {
        WRITER$.write(this, SpecificData.getEncoder(out));
    }

    @Override
    public void readExternal(java.io.ObjectInput in)
            throws java.io.IOException {
        READER$.read(this, SpecificData.getDecoder(in));
    }

    /**
     * RecordBuilder for UserSessionsValue instances.
     */
    public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<UserSessionsValue>
            implements org.apache.avro.data.RecordBuilder<UserSessionsValue> {

        private int anonymous_id;
        private java.lang.CharSequence action;
        private java.lang.CharSequence event_timestamp;
        private java.lang.CharSequence event_datetime;
        private java.lang.CharSequence session_id;

        /** Creates a new Builder */
        private Builder() {
            super(SCHEMA$);
        }

        /**
         * Creates a Builder by copying an existing Builder.
         * @param other The existing Builder to copy.
         */
        private Builder(com.demo.avro.UserSessionsValue.Builder other) {
            super(other);
            if (isValidValue(fields()[0], other.anonymous_id)) {
                this.anonymous_id = data().deepCopy(fields()[0].schema(), other.anonymous_id);
                fieldSetFlags()[0] = true;
            }
            if (isValidValue(fields()[1], other.action)) {
                this.action = data().deepCopy(fields()[1].schema(), other.action);
                fieldSetFlags()[1] = true;
            }
            if (isValidValue(fields()[2], other.event_timestamp)) {
                this.event_timestamp = data().deepCopy(fields()[2].schema(), other.event_timestamp);
                fieldSetFlags()[2] = true;
            }
            if (isValidValue(fields()[3], other.event_datetime)) {
                this.event_datetime = data().deepCopy(fields()[3].schema(), other.event_datetime);
                fieldSetFlags()[3] = true;
            }
            if (isValidValue(fields()[4], other.session_id)) {
                this.session_id = data().deepCopy(fields()[4].schema(), other.session_id);
                fieldSetFlags()[4] = true;
            }
        }

        /**
         * Creates a Builder by copying an existing UserSessionsValue instance
         * @param other The existing instance to copy.
         */
        private Builder(com.demo.avro.UserSessionsValue other) {
            super(SCHEMA$);
            if (isValidValue(fields()[0], other.anonymous_id)) {
                this.anonymous_id = data().deepCopy(fields()[0].schema(), other.anonymous_id);
                fieldSetFlags()[0] = true;
            }
            if (isValidValue(fields()[1], other.action)) {
                this.action = data().deepCopy(fields()[1].schema(), other.action);
                fieldSetFlags()[1] = true;
            }
            if (isValidValue(fields()[2], other.event_timestamp)) {
                this.event_timestamp = data().deepCopy(fields()[2].schema(), other.event_timestamp);
                fieldSetFlags()[2] = true;
            }
            if (isValidValue(fields()[3], other.event_datetime)) {
                this.event_datetime = data().deepCopy(fields()[3].schema(), other.event_datetime);
                fieldSetFlags()[3] = true;
            }
            if (isValidValue(fields()[4], other.session_id)) {
                this.session_id = data().deepCopy(fields()[4].schema(), other.session_id);
                fieldSetFlags()[4] = true;
            }
        }

        /**
         * Gets the value of the 'anonymous_id' field.
         * @return The value.
         */
        public java.lang.Integer getAnonymousId() {
            return anonymous_id;
        }

        /**
         * Sets the value of the 'anonymous_id' field.
         * @param value The value of 'anonymous_id'.
         * @return This builder.
         */
        public com.demo.avro.UserSessionsValue.Builder setAnonymousId(int value) {
            validate(fields()[0], value);
            this.anonymous_id = value;
            fieldSetFlags()[0] = true;
            return this;
        }

        /**
         * Checks whether the 'anonymous_id' field has been set.
         * @return True if the 'anonymous_id' field has been set, false otherwise.
         */
        public boolean hasAnonymousId() {
            return fieldSetFlags()[0];
        }


        /**
         * Clears the value of the 'anonymous_id' field.
         * @return This builder.
         */
        public com.demo.avro.UserSessionsValue.Builder clearAnonymousId() {
            fieldSetFlags()[0] = false;
            return this;
        }

        /**
         * Gets the value of the 'action' field.
         * @return The value.
         */
        public java.lang.CharSequence getAction() {
            return action;
        }

        /**
         * Sets the value of the 'action' field.
         * @param value The value of 'action'.
         * @return This builder.
         */
        public com.demo.avro.UserSessionsValue.Builder setAction(java.lang.CharSequence value) {
            validate(fields()[1], value);
            this.action = value;
            fieldSetFlags()[1] = true;
            return this;
        }

        /**
         * Checks whether the 'action' field has been set.
         * @return True if the 'action' field has been set, false otherwise.
         */
        public boolean hasAction() {
            return fieldSetFlags()[1];
        }


        /**
         * Clears the value of the 'action' field.
         * @return This builder.
         */
        public com.demo.avro.UserSessionsValue.Builder clearAction() {
            action = null;
            fieldSetFlags()[1] = false;
            return this;
        }

        /**
         * Gets the value of the 'event_timestamp' field.
         * @return The value.
         */
        public java.lang.CharSequence getEventTimestamp() {
            return event_timestamp;
        }

        /**
         * Sets the value of the 'event_timestamp' field.
         * @param value The value of 'event_timestamp'.
         * @return This builder.
         */
        public com.demo.avro.UserSessionsValue.Builder setEventTimestamp(java.lang.CharSequence value) {
            validate(fields()[2], value);
            this.event_timestamp = value;
            fieldSetFlags()[2] = true;
            return this;
        }

        /**
         * Checks whether the 'event_timestamp' field has been set.
         * @return True if the 'event_timestamp' field has been set, false otherwise.
         */
        public boolean hasEventTimestamp() {
            return fieldSetFlags()[2];
        }


        /**
         * Clears the value of the 'event_timestamp' field.
         * @return This builder.
         */
        public com.demo.avro.UserSessionsValue.Builder clearEventTimestamp() {
            event_timestamp = null;
            fieldSetFlags()[2] = false;
            return this;
        }

        /**
         * Gets the value of the 'event_datetime' field.
         * @return The value.
         */
        public java.lang.CharSequence getEventDatetime() {
            return event_datetime;
        }

        /**
         * Sets the value of the 'event_datetime' field.
         * @param value The value of 'event_datetime'.
         * @return This builder.
         */
        public com.demo.avro.UserSessionsValue.Builder setEventDatetime(java.lang.CharSequence value) {
            validate(fields()[3], value);
            this.event_datetime = value;
            fieldSetFlags()[3] = true;
            return this;
        }

        /**
         * Checks whether the 'event_datetime' field has been set.
         * @return True if the 'event_datetime' field has been set, false otherwise.
         */
        public boolean hasEventDatetime() {
            return fieldSetFlags()[3];
        }


        /**
         * Clears the value of the 'event_datetime' field.
         * @return This builder.
         */
        public com.demo.avro.UserSessionsValue.Builder clearEventDatetime() {
            event_datetime = null;
            fieldSetFlags()[3] = false;
            return this;
        }

        /**
         * Gets the value of the 'session_id' field.
         * @return The value.
         */
        public java.lang.CharSequence getSessionId() {
            return session_id;
        }

        /**
         * Sets the value of the 'session_id' field.
         * @param value The value of 'session_id'.
         * @return This builder.
         */
        public com.demo.avro.UserSessionsValue.Builder setSessionId(java.lang.CharSequence value) {
            validate(fields()[4], value);
            this.session_id = value;
            fieldSetFlags()[4] = true;
            return this;
        }

        /**
         * Checks whether the 'session_id' field has been set.
         * @return True if the 'session_id' field has been set, false otherwise.
         */
        public boolean hasSessionId() {
            return fieldSetFlags()[4];
        }


        /**
         * Clears the value of the 'session_id' field.
         * @return This builder.
         */
        public com.demo.avro.UserSessionsValue.Builder clearSessionId() {
            session_id = null;
            fieldSetFlags()[4] = false;
            return this;
        }

        @Override
        @SuppressWarnings("unchecked")
        public UserSessionsValue build() {
            try {
                UserSessionsValue record = new UserSessionsValue();
                record.anonymous_id = fieldSetFlags()[0] ? this.anonymous_id : (java.lang.Integer) defaultValue(fields()[0]);
                record.action = fieldSetFlags()[1] ? this.action : (java.lang.CharSequence) defaultValue(fields()[1]);
                record.event_timestamp = fieldSetFlags()[2] ? this.event_timestamp : (java.lang.CharSequence) defaultValue(fields()[2]);
                record.event_datetime = fieldSetFlags()[3] ? this.event_datetime : (java.lang.CharSequence) defaultValue(fields()[3]);
                record.session_id = fieldSetFlags()[4] ? this.session_id : (java.lang.CharSequence) defaultValue(fields()[4]);
                return record;
            } catch (java.lang.Exception e) {
                throw new org.apache.avro.AvroRuntimeException(e);
            }
        }
    }

}