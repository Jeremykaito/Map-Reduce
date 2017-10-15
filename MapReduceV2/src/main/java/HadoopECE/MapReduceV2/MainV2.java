package HadoopECE.MapReduceV2;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import HadoopECE.MapReduceV2.MainV2;

public class MainV2 
{
	public static void main( String[] args ) throws Exception
	{
		//Creation d'un job pour le map reduce
		Configuration conf = new Configuration();
		Job job = null;
		job = Job.getInstance(conf, "MainV2");
		job.setJarByClass(MainV2.class);
		job.setMapperClass(PivotMapper.class);
		job.setReducerClass(PivotReducer.class);
		job.setOutputKeyClass(LongWritable.class);
		job.setOutputValueClass(Text.class);
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
	//Mapper
	public static class PivotMapper extends Mapper<LongWritable, Text, LongWritable, Text> {
		@Override
		//Une map par ligne du csv
		protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			//Séparation des nombres en fonction des ; du csv
			String[] nombres = value.toString().split(";");
			//Concatenation de chaque nombre avec son numéro de ligne
			for (int i = 0; i<nombres.length;i++) {
				context.write(new LongWritable(i), new Text(key.get() + "&" + nombres[i]));
			}
		}
	}
	//Reducer
	public static class PivotReducer extends Reducer<LongWritable, Text, LongWritable, Text> {
		@Override
		protected void reduce(LongWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
			
			long new_ligne = key.get();
			Map<String,String> nombres = new TreeMap<String,String>();//map <new_colonne,nombre> triée
			String cvs_res = "";//resultat final
			//Ajout des nombres a la map avec le bon numero de colonne
			for (Text v : values) {
				nombres.put(v.toString().split("&")[0], v.toString().split("&")[1]);
			}
			//Creation du resultat final
			for(Map.Entry<String,String> entry : nombres.entrySet()) {
				cvs_res += entry.getValue() +";";
			}
			//Suppression du dernier point virgule
			cvs_res = cvs_res.substring(0, cvs_res.length() - 1);
			
			context.write(new LongWritable(new_ligne),  new Text(cvs_res));

		}
	}
}
