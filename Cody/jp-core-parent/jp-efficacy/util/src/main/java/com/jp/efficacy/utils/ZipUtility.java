/*
 * Copyright (c) Java Pathshala
 * All rights reserved.
 *
 * No parts of this source code can be reproduced without written consent from
 * Java Pathshala
 */
package com.jp.efficacy.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;
import java.util.zip.CheckedOutputStream;
import java.util.zip.Checksum;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 *
 *
 *
 * @author Dimit Chadha
 * @since 1.0
 * @version 1.0
 */
public class ZipUtility
{

    /**
     * The buffer size
     */
    private static final int BUFFER = 2048;

    /**
     * ZipUtility constructor comment.
     */
    public ZipUtility()
    {
        super();
    }

    /**
     * Writes a compressed input data stream through an output data decompressing stream filter
     * using the Adler32 algoritm to calculate the checksum required for the data decompression.
     *
     * @param packed   java.io.InputStream the compressed data stream
     * @param unpacked java.io.OutputStream the data stream to decompress
     * @throws IOException thrown if decompression fails
     */
    private Checksum checkedAdler32UnZip(InputStream packed, OutputStream unpacked)
            throws IOException
    {

        BufferedOutputStream dest = new BufferedOutputStream(unpacked, BUFFER);
        CheckedInputStream checksum = new CheckedInputStream(packed, new Adler32());
        ZipInputStream zis = new ZipInputStream(new BufferedInputStream(checksum));
        ZipEntry entry = zis.getNextEntry();
        int count;
        byte[] data = new byte[BUFFER];
        while ((count = zis.read(data, 0, BUFFER)) != -1)
        {
            dest.write(data, 0, count);
        }
        dest.flush();
        dest.close();
        zis.close();
        return checksum.getChecksum();
    }

    /**
     * Writes an input data stream through an output data compressing stream filter
     * using the Adler32 algoritm to calculate the checksum required for the data compression.
     *
     * @param zipEntryName java.io.InputStream compressed data entry name, eg file name
     * @param unpacked     java.io.InputStream the stream of data to compress
     * @param packed       java.io.OutputStream the compressed stream of data
     * @throws IOException thrown if data compression fails
     */
    private Checksum checkedAdler32Zip(String zipEntryName, InputStream unpacked, OutputStream packed)
            throws IOException
    {

        BufferedInputStream origin = new BufferedInputStream(unpacked, BUFFER);
        CheckedOutputStream checksum = new CheckedOutputStream(packed, new Adler32());
        ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(checksum));
        out.setMethod(ZipOutputStream.DEFLATED);
        byte[] data = new byte[BUFFER];
        ZipEntry entry = new ZipEntry(zipEntryName);
        out.putNextEntry(entry);
        int count;
        while ((count = origin.read(data, 0, BUFFER)) != -1)
        {
            out.write(data, 0, count);
        }
        origin.close();
        out.close();
        return checksum.getChecksum();
    }

    /**
     * Writes a compressed input data stream through an output data decompressing stream filter.
     *
     * @param packed   java.io.InputStream the compressed data stream
     * @param unpacked java.io.OutputStream the data stream to decompress
     * @throws IOException thrown if decompression fails
     */
    private void unCheckedUnZip(InputStream packed, OutputStream unpacked)
            throws IOException
    {

        BufferedOutputStream dest = new BufferedOutputStream(unpacked, BUFFER);
        ZipInputStream zis = new ZipInputStream(new BufferedInputStream(packed));
        ZipEntry entry = zis.getNextEntry();
        int count;
        byte[] data = new byte[BUFFER];
        while ((count = zis.read(data, 0, BUFFER)) != -1)
        {
            dest.write(data, 0, count);
        }
        dest.flush();
        dest.close();
        zis.close();
    }

    /**
     * Writes an input data stream through an output data compressing stream filter.
     *
     * @param zipEntryName java.io.InputStream compressed data entry name, eg file name
     * @param unpacked     java.io.InputStream the stream of data to compress
     * @param packed       java.io.OutputStream the compressed stream of data
     * @throws IOException thrown if data compression fails
     */
    private void unCheckedZip(String zipEntryName, InputStream unpacked, OutputStream packed)
            throws IOException
    {

        BufferedInputStream origin = new BufferedInputStream(unpacked, BUFFER);
        ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(packed));
        out.setMethod(ZipOutputStream.DEFLATED);
        byte[] data = new byte[BUFFER];
        ZipEntry entry = new ZipEntry(zipEntryName);
        out.putNextEntry(entry);
        int count;
        while ((count = origin.read(data, 0, BUFFER)) != -1)
        {
            out.write(data, 0, count);
        }
        origin.close();
        out.close();
    }

    /**
     * Writes a compressed input data stream through an output data decompressing stream filter either unchecked
     * or using the Adler32 algorithm to calculate the checksum required for the data compression.
     *
     * @param packed     java.io.InputStream the compressed data stream
     * @param unpacked   java.io.OutputStream the data stream to decompress
     * @param doChecksum boolean true if should calculate checksum, else false
     * @return Checksum the checksum for the data decompression, null if doChecksum is false
     *
     * @throws IOException thrown if decompression fails
     */
    public Checksum unZip(
            InputStream packed,
            OutputStream unpacked,
            boolean doChecksum)
            throws IOException
    {

        if (unpacked != null)
        {
            if (doChecksum)
            {
                return checkedAdler32UnZip(packed, unpacked);
            }
            else
            {
                unCheckedUnZip(packed, unpacked);
                return null;
            }
        }
        else
        {
            throw new ZipException("The output stream cannot be null");
        }
    }

    /**
     * Writes an input data stream through an output data compressing stream filter either unchecked
     * or using the Adler32 algorithm to calculate the checksum required for the data compression.
     * (Adler32 is known to be faster than the CRC32 algorithm and as reliable and is therefore used here)
     *
     * @param zipEntryName java.io.InputStream compressed data entry name, eg file name
     * @param unpacked     java.io.InputStream the stream of data to compress
     * @param packed       java.io.OutputStream the compressed stream of data
     * @param doChecksum   boolean true if should calculate checksum, else false
     * @return Checksum the checksum for the data compression, null if doChecksum is false
     *
     * @throws IOException thrown if data compression fails
     */
    public Checksum zip(
            String zipEntryName,
            InputStream unpacked,
            OutputStream packed,
            boolean doChecksum)
            throws IOException
    {

        if (zipEntryName != null)
        {
            if (packed != null)
            {
                if (doChecksum)
                {
                    return checkedAdler32Zip(zipEntryName, unpacked, packed);
                }
                else
                {
                    unCheckedZip(zipEntryName, unpacked, packed);
                    return null;
                }
            }
            else
            {
                throw new ZipException("The output stream cannot be null");
            }
        }
        else
        {
            throw new ZipException("The zip entry name cannot be null");
        }
    }

}
