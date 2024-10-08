import VideoPage from '../components/page/video_page'
import Layout from '../components/layout'
import Head from 'next/head'

export default function Video({ videoDetails }) {
   return <>
      <Layout>
         <div>
            <Head>
               <title>{videoDetails.title}</title>
            </Head>
         </div>
            <VideoPage videoDetails={videoDetails} />
      </Layout>
   </>
}                        
 
export async function getStaticPaths() {
   const videosRes = await fetch(`${process.env.API_URL}video/all`)
   const videos = await videosRes.json()

   const paths = videos.map((video) => ({
      params: { slug: video.slug },
   }))

   return { paths, fallback: false }
}
 
export async function getStaticProps({ params }) {
   const videoDetailsRes = await fetch(`${process.env.API_URL}video/details/${params.slug}`)
   const videoDetails = await videoDetailsRes.json()
            
   return {
      props: {
         videoDetails
      }             
   }
}