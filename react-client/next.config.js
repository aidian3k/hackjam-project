/** @type {import('next').NextConfig} */
const nextConfig = {
  reactStrictMode: false,
  images: {
    remotePatterns: [
      {
        protocol: 'https',
        hostname: 'aidian3k-bucket-test.s3.amazonaws.com',
        port: '',
        pathname: '/**'
      }
    ]
  }
};

module.exports = nextConfig;
