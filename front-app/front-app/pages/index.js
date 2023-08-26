import Layout from '../components/layout'
import CategoryContainer from '../components/index/category_container'
import IndexTitle from '../components/index/index_title'
import IndexCarousel from '../components/index/index_carousel'
import IndexFooter from '../components/index/index_footer'

export default function HomePage({ categories, videos, indexLayout }) {
   return (
      <Layout>

            <IndexTitle indexLayout={indexLayout}></IndexTitle>

            <IndexCarousel indexLayout={indexLayout}></IndexCarousel>

            <div>
               {categories.map((category) => (
                  <CategoryContainer key={category.id} category={category} videos={videos.filter(video => video.category === category.id)}/>
               ))}
            </div>

            <IndexFooter indexLayout={indexLayout}></IndexFooter>
      </Layout>
    )
}

export async function getStaticProps() {
   const categoriesRes = await fetch(`${process.env.API_URL}category/all`)
   const videosRes = await fetch(`${process.env.API_URL}video/all`)
   const indexLayoutRes = await fetch(`${process.env.API_URL}index-layout`)
   const categories = await categoriesRes.json()
   const videos = await videosRes.json()
   const indexLayout = await indexLayoutRes.json()

   return {
      props: {
         categories,
         videos,
         indexLayout
      }
   }
}